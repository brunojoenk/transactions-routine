package com.transactionsroutine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionsroutine.dtos.TransactionRequestDTO;
import com.transactionsroutine.dtos.TransactionResponseDTO;
import com.transactionsroutine.exceptions.AccountNotFoundException;
import com.transactionsroutine.services.TransactionCreation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

	private final TransactionCreation transactionCreation;

	@Autowired
	public TransactionController(
			TransactionCreation transactionCreation) {
		this.transactionCreation = transactionCreation;
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Transaction created"),
			@ApiResponse(code = 400, message = "Parameter invalid"),
			@ApiResponse(code = 404, message = "Account not found for create transaction")
	})
	@PostMapping
	public TransactionResponseDTO create(@RequestBody TransactionRequestDTO transactionRequestDTO) {
		return transactionCreation.create( transactionRequestDTO );
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity handleIllegalArgumentException(IllegalArgumentException exception) {
		return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( exception.getMessage() );
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity handleNotFoundException(AccountNotFoundException exception) {
		return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( exception.getMessage() );
	}

}

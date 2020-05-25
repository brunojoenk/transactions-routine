package com.transactionsroutine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionsroutine.dtos.AccountRequestDTO;
import com.transactionsroutine.dtos.AccountResponseDTO;
import com.transactionsroutine.exceptions.AccountNotFoundException;
import com.transactionsroutine.mappers.AccountMapper;
import com.transactionsroutine.services.AccountCreation;
import com.transactionsroutine.services.AccountRetrieval;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

	private final AccountCreation accountCreation;

	private final AccountRetrieval accountRetrieval;

	@Autowired
	public AccountController(AccountCreation accountCreation, AccountRetrieval accountRetrieval) {
		this.accountCreation = accountCreation;
		this.accountRetrieval = accountRetrieval;
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Account created"),
			@ApiResponse(code = 400, message = "Parameter invalid")
	})
	@PostMapping
	public AccountResponseDTO create(@RequestBody AccountRequestDTO accountRequestDTO) {
		return accountCreation.create( accountRequestDTO );
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Account founded"),
			@ApiResponse(code = 404, message = "Account not founded")
	})
	@GetMapping("/{accountId}")
	public AccountResponseDTO get(@PathVariable Long accountId) {
		return AccountMapper.toDTO( accountRetrieval.get( accountId ) );
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

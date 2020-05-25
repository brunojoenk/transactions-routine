package com.transactionsroutine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactionsroutine.dtos.TransactionRequestDTO;
import com.transactionsroutine.dtos.TransactionResponseDTO;
import com.transactionsroutine.mappers.TransactionMapper;
import com.transactionsroutine.repositories.TransactionRepository;

@Service
public class TransactionCreation {

	private final TransactionRepository transactionRepository;

	private final TransactionMapper transactionMapper;

	@Autowired
	public TransactionCreation(
			TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
		this.transactionRepository = transactionRepository;
		this.transactionMapper = transactionMapper;
	}

	public TransactionResponseDTO create(TransactionRequestDTO requestDTO) {
		guardIsNotNull( requestDTO );
		return transactionMapper
				.toResponseDTO( transactionRepository.save( transactionMapper.toEntity( requestDTO ) ) );
	}

	private static void guardIsNotNull(TransactionRequestDTO requestDTO) {
		if (requestDTO == null)
			throw new IllegalArgumentException( "Request body is mandatory!" );
	}
}

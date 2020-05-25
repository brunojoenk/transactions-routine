package com.transactionsroutine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactionsroutine.dtos.AccountRequestDTO;
import com.transactionsroutine.dtos.AccountResponseDTO;
import com.transactionsroutine.mappers.AccountMapper;
import com.transactionsroutine.repositories.AccountRepository;

@Service
public class AccountCreation {

	private final AccountRepository accountRepository;

	@Autowired
	public AccountCreation(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public AccountResponseDTO create(AccountRequestDTO requestDTO) {
		guardIsNotNull( requestDTO );
		return AccountMapper.toDTO( accountRepository.save( AccountMapper.toEntity( requestDTO ) ) );
	}

	private static void guardIsNotNull(AccountRequestDTO requestDTO) {
		if (requestDTO == null)
			throw new IllegalArgumentException( "Request body is mandatory!" );
	}

}

package com.transactionsroutine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactionsroutine.entities.Account;
import com.transactionsroutine.exceptions.AccountNotFoundException;
import com.transactionsroutine.repositories.AccountRepository;

@Service
public class AccountRetrieval {

	private final AccountRepository accountRepository;

	@Autowired
	public AccountRetrieval(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Account get(Long id) {
		return accountRepository.findById( id )
				.orElseThrow( () -> new AccountNotFoundException( id ) );
	}
}

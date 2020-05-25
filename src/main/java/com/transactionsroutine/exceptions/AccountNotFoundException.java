package com.transactionsroutine.exceptions;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(Long id) {
		super( "Account not founded with id " + id );
	}

}

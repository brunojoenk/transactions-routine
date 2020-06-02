package com.transactionsroutine.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transactionsroutine.entities.Account;
import com.transactionsroutine.exceptions.AccountNotFoundException;
import com.transactionsroutine.repositories.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class AccountRetrievalTest {

	private static final Long ACCOUNT_ID = 1L;
	private static final String DOCUMENT_NUMBER = "123";

	@InjectMocks
	private AccountRetrieval accountRetrieval;

	@Mock
	private AccountRepository accountRepository;

	@Test
	@DisplayName("should retrieve account")
	public void shouldRetrieve() {
		doReturn( Optional.of( anAccount() ) ).when( accountRepository ).findById( ACCOUNT_ID );

		Account account = accountRetrieval.get( ACCOUNT_ID );

		assertThat( account.getId(), equalTo( ACCOUNT_ID ) );
		assertThat( account.getDocumentNumber(), equalTo( DOCUMENT_NUMBER ) );
	}

	@Test
	@DisplayName("when account is not found")
	public void whenAccountDoesNotExist() {
		assertThrows( AccountNotFoundException.class,
				() -> accountRetrieval.get( ACCOUNT_ID ) );
	}

	private Account anAccount() {
		return Account.builder().id( ACCOUNT_ID ).documentNumber( DOCUMENT_NUMBER ).build();
	}
}

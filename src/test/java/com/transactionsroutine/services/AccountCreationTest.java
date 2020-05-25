package com.transactionsroutine.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transactionsroutine.dtos.AccountRequestDTO;
import com.transactionsroutine.dtos.AccountResponseDTO;
import com.transactionsroutine.entities.Account;
import com.transactionsroutine.repositories.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class AccountCreationTest {

	private static final Long ACCOUNT_ID = 1L;
	private static final String DOCUMENT_NUMBER = "123";

	@InjectMocks
	private AccountCreation accountCreation;

	@Mock
	private AccountRepository accountRepository;

	@Test
	@DisplayName("should create account")
	public void shouldCreate() {
		AccountRequestDTO requestDTO = anAccountRequest();
		Mockito.doReturn( anAccount() ).when( accountRepository )
				.save( Account.builder().documentNumber( DOCUMENT_NUMBER ).build() );

		AccountResponseDTO responseDTO = accountCreation.create( requestDTO );

		assertThat( responseDTO.getId(), equalTo( ACCOUNT_ID ) );
		assertThat( responseDTO.getDocumentNumber(), equalTo( DOCUMENT_NUMBER ) );
	}

	@Test
	@DisplayName("should return an error when request body is null")
	public void whenRequestBodyIsNull() {
		assertThrows( IllegalArgumentException.class,
				() -> accountCreation.create( null ) );
	}

	private AccountRequestDTO anAccountRequest() {
		return AccountRequestDTO.builder().documentNumber( DOCUMENT_NUMBER ).build();
	}

	private Account anAccount() {
		return Account.builder().id( ACCOUNT_ID ).documentNumber( DOCUMENT_NUMBER ).build();
	}

}

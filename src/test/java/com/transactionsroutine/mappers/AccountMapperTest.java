package com.transactionsroutine.mappers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.transactionsroutine.dtos.AccountRequestDTO;
import com.transactionsroutine.dtos.AccountResponseDTO;
import com.transactionsroutine.entities.Account;

public class AccountMapperTest {

	private static final String DOCUMENT_NUMBER = "123";

	@Test
	@DisplayName("should mapper request to entity")
	public void shouldMapperRequestToEntity() {
		Account account = AccountMapper.toEntity( anAccountRequestDTO() );

		assertThat( account.getDocumentNumber(), equalTo( DOCUMENT_NUMBER ) );
	}

	@Test
	@DisplayName("should mapper entity to dto")
	public void shouldMapperEntityToDTO() {
		AccountResponseDTO responseDTO = AccountMapper.toDTO( anAccount() );

		assertThat( responseDTO.getDocumentNumber(), equalTo( DOCUMENT_NUMBER ) );
	}

	private AccountRequestDTO anAccountRequestDTO() {
		return AccountRequestDTO.builder()
				.documentNumber( DOCUMENT_NUMBER )
				.build();
	}

	private Account anAccount() {
		return Account.builder()
				.documentNumber( DOCUMENT_NUMBER )
				.build();
	}
}

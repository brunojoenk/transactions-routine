package com.transactionsroutine.mappers;

import org.springframework.util.Assert;

import com.transactionsroutine.dtos.AccountRequestDTO;
import com.transactionsroutine.dtos.AccountResponseDTO;
import com.transactionsroutine.entities.Account;

public class AccountMapper {

	public static Account toEntity(AccountRequestDTO requestDTO) {
		Assert.hasText( requestDTO.getDocumentNumber(), "Field document number is mandatory!" );
		return Account.builder()
				.documentNumber( requestDTO.getDocumentNumber() )
				.build();
	}

	public static AccountResponseDTO toDTO(Account account) {
		return AccountResponseDTO.builder()
				.id( account.getId() )
				.documentNumber( account.getDocumentNumber() )
				.build();
	}

}

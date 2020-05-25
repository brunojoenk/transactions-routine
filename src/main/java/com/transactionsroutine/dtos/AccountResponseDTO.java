package com.transactionsroutine.dtos;

import lombok.Builder;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
public class AccountResponseDTO {

	@JsonProperty("account_id")
	private final Long id;

	@JsonProperty("document_number")
	private final String documentNumber;
}

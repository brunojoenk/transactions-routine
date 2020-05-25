package com.transactionsroutine.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {

	@JsonProperty("document_number")
	private String documentNumber;
}

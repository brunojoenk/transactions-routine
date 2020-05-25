package com.transactionsroutine.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {

	@JsonProperty("account_id")
	private Long accountId;

	@JsonProperty("operation_type_id")
	private Long operationTypeId;

	@JsonProperty("amount")
	private BigDecimal amount;
}

package com.transactionsroutine.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
public class TransactionResponseDTO {

	@JsonProperty("id")
	private final Long id;

	@JsonProperty("account_id")
	private final Long accountId;

	@JsonProperty("operation_type_id")
	private final Long operationTypeId;

	@JsonProperty("amount")
	private final BigDecimal amount;

	@JsonProperty("event_date")
	private final Date eventDate;

}

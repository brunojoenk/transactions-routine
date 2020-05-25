package com.transactionsroutine.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.transactionsroutine.enums.OperationType;

public class AmountConverterTest {

	private static final long PAY_CACHE_ID = 1L;
	private static final long PAY_IN_INSTALLMENTS_ID = 2L;
	private static final long WITHDRAW_ID = 3L;
	private static final BigDecimal TEN_NEGATIVE = BigDecimal.TEN.negate();

	@Test
	@DisplayName("when is positive operation")
	public void whenIsPositiveOperation() {
		MatcherAssert.assertThat( AmountConverter.getAmountByOperation( OperationType.PAYMENT, BigDecimal.TEN ), equalTo( BigDecimal.TEN ) );
	}

	@DisplayName("when is negative operation")
	@ParameterizedTest
	@ValueSource(longs = { PAY_CACHE_ID, PAY_IN_INSTALLMENTS_ID, WITHDRAW_ID })
	public void whenIsNegativeOperation(Long operationTypeId) {
		BigDecimal amount = AmountConverter
				.getAmountByOperation( OperationTypeRetrieval.getOperationType( operationTypeId ), BigDecimal.TEN );
		assertThat( amount, equalTo( TEN_NEGATIVE ) );
	}
}

package com.transactionsroutine.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OperationTypeRetrievalTest {

	private static final long PAY_CACHE_ID = 1L;
	private static final long PAY_IN_INSTALLMENTS_ID = 2L;
	private static final long WITHDRAW_ID = 3L;
	private static final long PAYMENT_ID = 4L;
	private static final long INVALID_ID = 5L;

	@DisplayName("when is valid operation type id")
	@ParameterizedTest
	@ValueSource(longs = { PAY_CACHE_ID, PAY_IN_INSTALLMENTS_ID, WITHDRAW_ID, PAYMENT_ID })
	public void whenIsValidOperationTypeId(Long operationTypeId) {
		assertThat( OperationTypeRetrieval.getOperationType( operationTypeId ).getOperationTypeId(),
				equalTo( operationTypeId ) );
	}

	@Test
	@DisplayName("when is invalid operation type id")
	public void whenIsInvalidOperationTypeId() {
		assertThrows( IllegalArgumentException.class, () -> OperationTypeRetrieval.getOperationType( INVALID_ID ) );
	}

}

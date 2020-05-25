package com.transactionsroutine.services;

import static com.transactionsroutine.services.AmountValidation.isPositiveAmount;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AmountValidationTest {

	@Test
	@DisplayName("when amount is negative")
	public void whenAmountIsNegative() {
		assertThrows( IllegalArgumentException.class, () -> isPositiveAmount( BigDecimal.TEN.negate() ) );
	}

	@Test
	@DisplayName("when amount is zero")
	public void whenAmountIsZero() {
		assertThrows( IllegalArgumentException.class, () -> isPositiveAmount( BigDecimal.ZERO ) );
	}

	@Test
	@DisplayName("when amount is null")
	public void whenAmountIsNull() {
		assertThrows( IllegalArgumentException.class, () -> isPositiveAmount( null ) );
	}

	@Test
	@DisplayName("when amount is more than zero")
	public void whenAmountIsMoreThanZero() {
		isPositiveAmount( BigDecimal.TEN );
		System.out.println( "doThis()" );
	}
}

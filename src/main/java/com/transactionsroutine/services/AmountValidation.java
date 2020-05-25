package com.transactionsroutine.services;

import java.math.BigDecimal;

public class AmountValidation {

	public static void isPositiveAmount(BigDecimal amount) {
		if (isNegativeOrZeroValue( amount ))
			throw new IllegalArgumentException( "The transaction value should be more than 0!" );
	}

	private static Boolean isNegativeOrZeroValue(BigDecimal amount) {
		return amount == null || BigDecimal.ZERO.compareTo( amount ) >= 0;
	}

}

package com.transactionsroutine.services;

import java.math.BigDecimal;

import com.transactionsroutine.enums.OperationType;

public class AmountConverter {

	public static BigDecimal getAmountByOperation(OperationType operationType, BigDecimal amount) {
		if (isPositiveOperation( operationType ))
			return amount;
		return amount.negate();
	}

	private static Boolean isPositiveOperation(OperationType operationType) {
		return OperationType.PAYMENT == operationType;
	}

}

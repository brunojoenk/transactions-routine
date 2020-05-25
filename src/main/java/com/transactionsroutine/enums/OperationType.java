package com.transactionsroutine.enums;

import java.util.Arrays;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationType {
	PAY_CACHE( 1L ),
	PAY_IN_INSTALLMENTS( 2L ),
	WITHDRAW( 3L ),
	PAYMENT( 4L );

	private final Long operationTypeId;

	public static Optional<OperationType> getOperationById(Long id) {
		return Arrays.stream( OperationType.values() )
				.filter( operationType -> operationType.getOperationTypeId().equals( id ) )
				.findFirst();
	}

}

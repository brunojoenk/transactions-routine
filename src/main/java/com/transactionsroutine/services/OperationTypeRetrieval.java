package com.transactionsroutine.services;

import com.transactionsroutine.enums.OperationType;

public class OperationTypeRetrieval {

	public static OperationType getOperationType(Long operationTypeId) {
		return OperationType.getOperationById( operationTypeId )
				.orElseThrow( () -> new IllegalArgumentException( "Operation type not found: " + operationTypeId ) );
	}

}

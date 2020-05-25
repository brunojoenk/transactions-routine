package com.transactionsroutine.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactionsroutine.dtos.TransactionRequestDTO;
import com.transactionsroutine.dtos.TransactionResponseDTO;
import com.transactionsroutine.entities.Transaction;
import com.transactionsroutine.enums.OperationType;
import com.transactionsroutine.services.AccountRetrieval;
import com.transactionsroutine.services.AmountConverter;
import com.transactionsroutine.services.AmountValidation;
import com.transactionsroutine.services.OperationTypeRetrieval;

@Service
public class TransactionMapper {

	private final AccountRetrieval accountRetrieval;

	@Autowired
	public TransactionMapper(AccountRetrieval accountRetrieval) {
		this.accountRetrieval = accountRetrieval;
	}

	public Transaction toEntity(TransactionRequestDTO requestDTO) {
		AmountValidation.isPositiveAmount( requestDTO.getAmount() );
		OperationType operationType = OperationTypeRetrieval.getOperationType( requestDTO.getOperationTypeId() );
		return Transaction.builder()
				.account( accountRetrieval.get( requestDTO.getAccountId() ) )
				.operationType( operationType )
				.amount( AmountConverter.getAmountByOperation( operationType, requestDTO.getAmount() ) )
				.build();
	}

	public TransactionResponseDTO toResponseDTO(Transaction transaction) {
		return TransactionResponseDTO.builder()
				.id( transaction.getId() )
				.accountId( transaction.getAccount().getId() )
				.amount( transaction.getAmount() )
				.eventDate( transaction.getEventDate() )
				.operationTypeId( transaction.getOperationType().getOperationTypeId() )
				.build();
	}

}

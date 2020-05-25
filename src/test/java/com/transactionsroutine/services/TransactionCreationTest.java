package com.transactionsroutine.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transactionsroutine.dtos.TransactionRequestDTO;
import com.transactionsroutine.entities.Transaction;
import com.transactionsroutine.mappers.TransactionMapper;
import com.transactionsroutine.repositories.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionCreationTest {

	@InjectMocks
	private TransactionCreation transactionCreation;

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private TransactionMapper transactionMapper;

	@Test
	@DisplayName("should create transaction")
	public void shouldCreate() {
		TransactionRequestDTO requestDTO = aTransactionRequest();
		Transaction expectedTransaction = aTransaction();
		doReturn( expectedTransaction ).when( transactionMapper ).toEntity( requestDTO );

		transactionCreation.create( requestDTO );

		verify( transactionRepository ).save( expectedTransaction );
	}

	@Test
	@DisplayName("should return an error when request body is null")
	public void whenRequestBodyIsNull(){
		assertThrows( IllegalArgumentException.class,
				() -> transactionCreation.create( null ) );
	}

	private TransactionRequestDTO aTransactionRequest() {
		return TransactionRequestDTO.builder()
				.amount( BigDecimal.TEN )
				.build();
	}

	private Transaction aTransaction() {
		return Transaction.builder()
				.amount( BigDecimal.TEN )
				.build();
	}
}

package com.transactionsroutine.mappers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transactionsroutine.dtos.TransactionRequestDTO;
import com.transactionsroutine.entities.Account;
import com.transactionsroutine.entities.Transaction;
import com.transactionsroutine.enums.OperationType;
import com.transactionsroutine.services.AccountRetrieval;

@ExtendWith(MockitoExtension.class)
public class TransactionMapperTest {

	private static final Long ACCOUNT_ID = 1L;
	private static final String DOCUMENT_NUMBER = "123";

	@InjectMocks
	private TransactionMapper transactionMapper;

	@Mock
	private AccountRetrieval accountRetrieval;

	@Test
	@DisplayName("should mapper request to entity")
	public void shouldMapperRequestToEntity() {
		Mockito.doReturn( anAccount() ).when( accountRetrieval ).get( ACCOUNT_ID );

		Transaction transaction = transactionMapper.toEntity( aTransactionRequestDTO() );

		assertThat( transaction.getAccount().getId(), equalTo( ACCOUNT_ID ) );
		assertThat( transaction.getAmount(), equalTo( BigDecimal.TEN ) );
		assertThat( transaction.getOperationType(), equalTo( OperationType.PAYMENT ) );
		assertThat( transaction.getEventDate(), equalTo( null ) );
		assertThat( transaction.getId(), equalTo( null ) );
	}

	private Account anAccount() {
		return Account.builder()
				.id( ACCOUNT_ID )
				.documentNumber( DOCUMENT_NUMBER ).build();
	}

	private TransactionRequestDTO aTransactionRequestDTO() {
		return TransactionRequestDTO.builder()
				.amount( BigDecimal.TEN )
				.accountId( ACCOUNT_ID )
				.operationTypeId( OperationType.PAYMENT.getOperationTypeId() )
				.build();
	}

}

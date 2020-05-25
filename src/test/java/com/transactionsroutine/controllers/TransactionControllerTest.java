package com.transactionsroutine.controllers;

import static com.transactionsroutine.enums.OperationType.PAYMENT;
import static com.transactionsroutine.enums.OperationType.WITHDRAW;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.transactionsroutine.TransactionsRoutineApplication;
import com.transactionsroutine.dtos.AccountRequestDTO;
import com.transactionsroutine.dtos.AccountResponseDTO;
import com.transactionsroutine.dtos.TransactionRequestDTO;
import com.transactionsroutine.dtos.TransactionResponseDTO;
import com.transactionsroutine.enums.OperationType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TransactionsRoutineApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldCreatePaymentTransaction() {
		AccountResponseDTO accountResponseDTO = createAccount().getBody();
		ResponseEntity<TransactionResponseDTO> responseCreate = restTemplate
				.postForEntity( "/transactions", aTransactionRequestDTO( accountResponseDTO.getId(), PAYMENT ),
						TransactionResponseDTO.class );

		assertThat( responseCreate.getStatusCode(), equalTo( HttpStatus.OK ) );
		assertThat( responseCreate.getBody().getId(), equalTo( 1L ) );
		assertThat( responseCreate.getBody().getAccountId(), equalTo( accountResponseDTO.getId() ) );
		assertThat( responseCreate.getBody().getAmount(), equalTo( BigDecimal.TEN ) );
		assertThat( responseCreate.getBody().getOperationTypeId(), equalTo( PAYMENT.getOperationTypeId() ) );
		assertThat( responseCreate.getBody().getEventDate() != null, equalTo( true ) );
	}

	@Test
	public void shouldCreateWithdrawTransaction() {
		AccountResponseDTO accountResponseDTO = createAccount().getBody();
		ResponseEntity<TransactionResponseDTO> responseCreate = restTemplate
				.postForEntity( "/transactions", aTransactionRequestDTO( accountResponseDTO.getId(), WITHDRAW ),
						TransactionResponseDTO.class );

		assertThat( responseCreate.getStatusCode(), equalTo( HttpStatus.OK ) );
		assertThat( responseCreate.getBody().getId(), equalTo( 1L ) );
		assertThat( responseCreate.getBody().getAccountId(), equalTo( accountResponseDTO.getId() ) );
		assertThat( responseCreate.getBody().getAmount(), equalTo( BigDecimal.TEN.negate() ) );
		assertThat( responseCreate.getBody().getOperationTypeId(), equalTo( WITHDRAW.getOperationTypeId() ) );
		assertThat( responseCreate.getBody().getEventDate() != null, equalTo( true ) );
	}

	@Test
	public void whenHasInvalidArgument() {
		ResponseEntity responseCreate = restTemplate
				.postForEntity( "/transactions", new TransactionRequestDTO(), String.class );

		assertThat( responseCreate.getStatusCode(), equalTo( HttpStatus.BAD_REQUEST ) );
	}

	@Test
	public void whenAccountDoesNotExist() {
		ResponseEntity responseCreate = restTemplate
				.postForEntity( "/transactions", aTransactionRequestDTO( 5L, PAYMENT ), String.class );

		assertThat( responseCreate.getStatusCode(), equalTo( HttpStatus.NOT_FOUND ) );
	}

	@Test
	public void whenOperationTypeIsInvalid() {
		AccountResponseDTO accountResponseDTO = createAccount().getBody();
		ResponseEntity responseCreate = restTemplate
				.postForEntity( "/transactions",
						aTransactionRequestDTOWithInvalidOperation( accountResponseDTO.getId() ), String.class );

		assertThat( responseCreate.getStatusCode(), equalTo( HttpStatus.BAD_REQUEST ) );
	}

	private TransactionRequestDTO aTransactionRequestDTO(Long accountId, OperationType operationType) {
		return TransactionRequestDTO.builder()
				.operationTypeId( operationType.getOperationTypeId() )
				.accountId( accountId )
				.amount( BigDecimal.TEN )
				.build();
	}

	private TransactionRequestDTO aTransactionRequestDTOWithInvalidOperation(Long accountId) {
		return TransactionRequestDTO.builder()
				.operationTypeId( 5L )
				.accountId( accountId )
				.amount( BigDecimal.TEN )
				.build();
	}

	private ResponseEntity<AccountResponseDTO> createAccount() {
		return restTemplate
				.postForEntity( "/accounts", AccountRequestDTO.builder().documentNumber( "123" ).build(),
						AccountResponseDTO.class );
	}
}

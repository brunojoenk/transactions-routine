package com.transactionsroutine.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.transactionsroutine.TransactionsRoutineApplication;
import com.transactionsroutine.dtos.AccountRequestDTO;
import com.transactionsroutine.dtos.AccountResponseDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TransactionsRoutineApplication.class)
public class AccountControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldCreateAndRetrieval() {
		ResponseEntity<AccountResponseDTO> responseCreate = createAccount();
		Long accountId = responseCreate.getBody().getId();

		ResponseEntity<AccountResponseDTO> responseRetrieval = restTemplate
				.getForEntity( "/accounts/" + accountId, AccountResponseDTO.class );

		assertThat( responseCreate.getStatusCode(), equalTo( HttpStatus.OK ) );
		assertThat( responseRetrieval.getStatusCode(), equalTo( HttpStatus.OK ) );
		assertThat( responseRetrieval.getBody().getDocumentNumber(), equalTo( "123" ) );
	}

	@Test
	public void whenHasInvalidArgument() {
		ResponseEntity responseEntity = restTemplate
				.postForEntity( "/accounts", AccountRequestDTO.builder().build(), String.class );

		assertThat( responseEntity.getStatusCode(), equalTo( HttpStatus.BAD_REQUEST ) );
	}

	@Test
	public void whenAccountIsNotFounded() {
		ResponseEntity responseRetrieval = restTemplate.getForEntity( "/accounts/20", String.class );

		assertThat( responseRetrieval.getStatusCode(), equalTo( HttpStatus.NOT_FOUND ) );
	}

	private ResponseEntity<AccountResponseDTO> createAccount() {
		return restTemplate
				.postForEntity( "/accounts", AccountRequestDTO.builder().documentNumber( "123" ).build(),
						AccountResponseDTO.class );
	}

}

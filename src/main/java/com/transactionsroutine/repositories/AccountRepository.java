package com.transactionsroutine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transactionsroutine.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

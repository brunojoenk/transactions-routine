package com.transactionsroutine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transactionsroutine.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

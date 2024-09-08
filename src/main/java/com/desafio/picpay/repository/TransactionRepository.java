package com.desafio.picpay.repository;

import com.desafio.picpay.entity.Transaction;
import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {
}

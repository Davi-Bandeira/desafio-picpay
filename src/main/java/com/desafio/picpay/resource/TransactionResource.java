package com.desafio.picpay.resource;

import java.util.List;

import com.desafio.picpay.entity.Transaction;
import com.desafio.picpay.service.TransactionService;
import jakarta.transaction.InvalidTransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("transaction")
public class TransactionResource {
    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> list() {
        return transactionService.findAll();
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) throws InvalidTransactionException {
        return transactionService.create(transaction);
    }
}

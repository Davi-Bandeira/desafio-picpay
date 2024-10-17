package com.desafio.picpay.service;

import com.desafio.picpay.entity.Transaction;
import com.desafio.picpay.entity.Wallet;
import com.desafio.picpay.enumerated.WalletType;
import com.desafio.picpay.repository.TransactionRepository;
import com.desafio.picpay.repository.WalletRepository;
import jakarta.transaction.InvalidTransactionException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    @Transactional
    public Transaction create(Transaction transaction) throws InvalidTransactionException {
        var walletPayer = walletRepository.findById(transaction.getPayer())
                .orElseThrow(() -> new InvalidTransactionException("Not found payer | id: " + transaction.getPayer()));
        var walletPayee = walletRepository.findById(transaction.getPayee())
                .orElseThrow(() -> new InvalidTransactionException("Not found payee | id: " + transaction.getPayee()));

        LOGGER.info("validating transaction {}...", transaction);
        if (isTransactionValid(walletPayer, walletPayee, transaction.getAmount()))
            throw new InvalidTransactionException("Invalid Transaction - " + transaction);

        var newTransaction = transactionRepository.save(transaction);

        walletRepository.save(walletPayer.debit(transaction.getAmount()));
        walletRepository.save(walletPayee.credit(transaction.getAmount()));

        authorizerService.authorize(transaction);

        notificationService.notify(transaction);

        return newTransaction;
    }

    private boolean isTransactionValid(Wallet payer, Wallet payee, BigDecimal value) {
        return payer.getWalletType().equals(WalletType.COMMON) &&
               payer.getBalance().compareTo(value) >= 0 &&
               !payer.getId().equals(payee.getId());
    }

    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}

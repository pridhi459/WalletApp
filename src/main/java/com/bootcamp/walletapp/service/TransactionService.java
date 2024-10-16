package com.bootcamp.walletapp.service;

import com.bootcamp.walletapp.entity.Transaction;
import com.bootcamp.walletapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionHistory(Long walletId) {
        List <Transaction> transactions = transactionRepository.findByWallet_WalletId(walletId);
        // transactionRepository.findByWallet_WalletId(walletId);
        return transactions;
    }
}

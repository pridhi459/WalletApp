package com.bootcamp.walletapp.service;

import com.bootcamp.walletapp.entity.Transaction;
import com.bootcamp.walletapp.exception.WalletNotFoundException;
import com.bootcamp.walletapp.repository.TransactionRepository;
import com.bootcamp.walletapp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    public List<Transaction> getTransactionHistory(Long walletId) {
        if(walletRepository.findByWalletId(walletId)==null){
            throw new WalletNotFoundException("Wallet does not exist");
        }
        List <Transaction> transactions = transactionRepository.findByWallet_WalletId(walletId);
        // transactionRepository.findByWallet_WalletId(walletId);
        return transactions;
    }
}

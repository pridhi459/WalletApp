package com.bootcamp.walletapp.repository;

import com.bootcamp.walletapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    List<Transaction> findByWallet_WalletId(long WalletID);

    List<Transaction> findByUser_UserId(Long userId);
}


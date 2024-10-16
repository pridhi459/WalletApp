package com.bootcamp.walletapp.dto;

import com.bootcamp.walletapp.entity.Transaction;
import com.bootcamp.walletapp.enums.TransactionType;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class TransactionResponse {


    private double amount;
    private TransactionType transactionType;
    private String username;
    private String toUsername;

    public TransactionResponse(@NotNull Transaction transaction) {
        this.amount = transaction.getAmount();
        this.transactionType = transaction.getTransactionType();
        this.username = transaction.getWallet().getUser().getUsername();
        this.toUsername = transaction.getTargetWallet().getUser().getUsername();
    }

}

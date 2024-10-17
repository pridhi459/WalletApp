package com.bootcamp.walletapp.dto;

import com.bootcamp.walletapp.entity.Transaction;
import com.bootcamp.walletapp.enums.TransactionType;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Data
public class TransactionResponse {


    private double amount;
    private TransactionType transactionType;
    private String fromUsername;
    private String toUsername;
    private LocalDateTime CreatedAt;


    public TransactionResponse(@NotNull Transaction transaction) {
        if(transaction.getWallet()==null){
            this.fromUsername="Self";
        }
        else {
            this.fromUsername = transaction.getWallet().getUser().getUsername();
        }
        this.amount = transaction.getAmount();
        this.transactionType = transaction.getTransactionType();

        this.toUsername = transaction.getTargetWallet().getUser().getUsername();
        this.CreatedAt = transaction.getCreatedAt();
    }

}

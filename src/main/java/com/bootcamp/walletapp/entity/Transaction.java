package com.bootcamp.walletapp.entity;

import com.bootcamp.walletapp.enums.CurrencyType;
import com.bootcamp.walletapp.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

//    @ManyToOne()
//    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "userId")
//    private User user;

    @Column(name = "amount", nullable = false)
    double amount;

    @Enumerated(EnumType.STRING)
    @Column(name="Currency", nullable = false)
    private CurrencyType curr_Type;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    TransactionType transactionType;

    @ManyToOne()
    @JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id")
    private Wallet wallet=null;

    @ManyToOne()
    @JoinColumn(name = "target_walletId", nullable = false, referencedColumnName = "wallet_id")
    private Wallet targetWallet;

    @Column(name="timestamp", nullable = false)
    private LocalDateTime CreatedAt = LocalDateTime.now();

    public Transaction(double amount, TransactionType transactionType, Wallet wallet, Wallet targetWallet) {

        this.curr_Type=wallet.getCurr_Type();
        this.amount = amount;
        this.transactionType = transactionType;
        this.wallet = wallet;
        this.targetWallet = targetWallet;
    }

    public Transaction(double amount, TransactionType transactionType, Wallet targetWallet) {

        this.curr_Type=targetWallet.getCurr_Type();
        this.amount = amount;
        this.transactionType = transactionType;
        this.targetWallet = targetWallet;
    }
}

package com.bootcamp.walletapp.entity;

import com.bootcamp.walletapp.enums.TransactionType;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name ="Transactions")
public class Transaction {


    @ManyToOne()
    @JoinColumn(name = "user_id",nullable = false, referencedColumnName = "userId")
    private User user;

    @Column(name ="amount", nullable = false)
    double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    TransactionType transactionType;


    @ManyToOne()
    @JoinColumn(name="wallet_id", referencedColumnName = "wallet_id")
    private Wallet wallet;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

    @ManyToOne()
    @JoinColumn(name = "To_from_userId",nullable = false, referencedColumnName = "userId")
    //@Column(name = "to_user", nullable = false)
    private User toFromUser;


    public Transaction(User user, double amount, TransactionType transactionType, Wallet wallet, User toFromUser) {
        this.user = user;
        this.amount = amount;
        this.transactionType = transactionType;
        this.wallet = wallet;
        this.toFromUser = toFromUser;
    }
}

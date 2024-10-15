package com.bootcamp.walletapp.entity;

import com.bootcamp.walletapp.enums.TransactionType;
import com.bootcamp.walletapp.exception.InsufficientBalanceException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name ="wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="wallet_id", nullable = false)
    private long walletId;

    @Column(name="balance", nullable = false)
    private double balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false, referencedColumnName = "userId")
    private User user;

    @Transient
    private List<Transaction> transactions=new ArrayList<>();

    public Wallet(User user) {
        this.user=user;
        this.balance=0;
    }

    public Wallet() {

    }

    public double deposit(double amount) {
        if (amount<0) {
            throw new IllegalArgumentException("Amount cannot be Less than Zero");
        }
        this.balance+=amount;
        transactions.add(new Transaction(this.user, amount, TransactionType.DEPOSIT, this, this.user));
        return this.balance;
    }


    public double withdrawal(double amount) {
        if (amount<0) {
            throw new IllegalArgumentException("Amount cannot be Less than Zero");
        }
        if (this.balance<amount) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        this.balance-=amount;
        transactions.add(new Transaction(this.user, amount, TransactionType.WITHDRAW, this, this.user));
        return amount;
    }

    public void transfer(double amount, Wallet toWallet) {
        if (amount<0) {
            throw new IllegalArgumentException("Amount cannot be Less than Zero");
        }
        if (this.balance<amount) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        this.balance-=amount;
        toWallet.FromDeposit(amount, this);
        transactions.add(new Transaction(this.user, amount, TransactionType.TRANSFER, this, toWallet.getUser()));
    }

    private void FromDeposit(double amount, Wallet fromWallet) {
        this.balance+=amount;
        transactions.add(new Transaction(this.user, amount, TransactionType.DEPOSIT, this, fromWallet.getUser()));

    }

    private User getUser() {
        return this.user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }


}

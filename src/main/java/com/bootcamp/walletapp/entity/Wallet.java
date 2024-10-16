package com.bootcamp.walletapp.entity;

import com.bootcamp.walletapp.enums.CurrencyType;
import com.bootcamp.walletapp.enums.TransactionType;
import com.bootcamp.walletapp.exception.InsufficientBalanceException;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id", nullable = false)
    private long walletId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "userId")
    private User user;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(name="Currency", nullable = false)
    private CurrencyType curr_Type;

    @Transient
    private List<Transaction> transactions = new ArrayList<>();

    public Wallet(User user) {
        this.curr_Type= CurrencyType.USD;
        this.user = user;
        this.balance = 0;
    }

    public double deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be Less than Zero");
        }
        this.balance += amount;
        transactions.add(new Transaction(amount, TransactionType.DEPOSIT, this, this));
        return this.balance;
    }


    public double withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be Less than Zero");
        }
        if (this.balance < amount) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        this.balance -= amount;
        transactions.add(new Transaction(amount, TransactionType.WITHDRAW, this, this));
        return amount;
    }

    public void transfer(double amount, Wallet toWallet) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be Less than Zero");
        }
        if (this.balance < amount) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        this.balance -= amount;
        double newAmount = amount;
        if(this.curr_Type != toWallet.curr_Type){
            newAmount = convertCurrency(amount, this.curr_Type, toWallet.curr_Type);
        }
        toWallet.transferalDeposit(newAmount, this);
        transactions.add(new Transaction(amount, TransactionType.TRANSFER, this, toWallet));
    }

    private double convertCurrency(double amount, CurrencyType currType, CurrencyType currType1) {
        return currType1.convertFromUSD(currType.convertToUSD(amount));
    }

    private void transferalDeposit(double amount, Wallet fromWallet) {
        this.balance += amount;
        transactions.add(new Transaction(amount, TransactionType.DEPOSIT, this, fromWallet));

    }

    public User getUser() {
        return this.user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }


    public CurrencyType getCurr_Type() {
        return curr_Type;
    }
}

package com.bootcamp.walletapp.service;

import com.bootcamp.walletapp.entity.User;
import com.bootcamp.walletapp.entity.Wallet;
import com.bootcamp.walletapp.exception.UserNotFoundException;
import com.bootcamp.walletapp.repository.TransactionRepository;
import com.bootcamp.walletapp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public double deposit(long userId, double amount) {
        double newBalance=0;
        try{
        Wallet wallet = walletRepository.findByUser_UserId(userId);

        if (wallet == null) {
            throw new UserNotFoundException("User not found");
        }

        newBalance = wallet.deposit(amount);
        walletRepository.save(wallet);
        transactionRepository.save(wallet.getTransactions().get(wallet.getTransactions().size() - 1));}
        catch (Exception e){
            System.out.println(e.getCause()+" "+e.getMessage());
        }
        return newBalance;
    }

    public double withdraw(Long userId, double amount) {
        double drawnAmount=0;
        try{
        Wallet wallet = walletRepository.findByUser_UserId(userId);

        if (wallet == null) {
            throw new UserNotFoundException("User not found");
        }

         drawnAmount = wallet.withdrawal(amount);
        walletRepository.save(wallet);
        transactionRepository.save(wallet.getTransactions().get(wallet.getTransactions().size() - 1));}
        catch (Exception e){
            System.out.println(e);
        }
        return drawnAmount;
    }

    public void transfer(Long userId, double amount, Long toUserId) {
        Wallet fromWallet = walletRepository.findByUser_UserId(userId);
        Wallet toWallet = walletRepository.findByUser_UserId(toUserId);

        if (fromWallet == null || toWallet == null) {
            throw new UserNotFoundException("User not found");
        }

        fromWallet.transfer(amount, toWallet);
        transactionRepository.save(toWallet.getTransactions().get(toWallet.getTransactions().size() - 1));
        transactionRepository.save(fromWallet.getTransactions().get(fromWallet.getTransactions().size() - 1));
        walletRepository.save(fromWallet);
        walletRepository.save(toWallet);

    }
}

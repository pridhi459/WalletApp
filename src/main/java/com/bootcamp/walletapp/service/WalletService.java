package com.bootcamp.walletapp.service;

import com.bootcamp.walletapp.entity.Wallet;
import com.bootcamp.walletapp.exception.UserNotFoundException;
import com.bootcamp.walletapp.exception.WalletNotFoundException;
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

    public double deposit(long walletId, double amount) {
        double newBalance=0;
        try{
        Wallet wallet = walletRepository.findByWalletId(walletId);

        if (wallet == null) {
            throw new WalletNotFoundException("Wallet not found");
        }

        newBalance = wallet.deposit(amount);
        walletRepository.save(wallet);
        recordTransaction(wallet);
        }
        catch (Exception e){
            System.out.println(e.getCause()+" "+e.getMessage());
        }
        return newBalance;
    }

    public double withdraw(Long walletId, double amount) {
        double drawnAmount=0;
        try{
        Wallet wallet = walletRepository.findByWalletId(walletId);

        if (wallet == null) {
            throw new WalletNotFoundException("Wallet not found");
        }

         drawnAmount = wallet.withdraw(amount);
        walletRepository.save(wallet);
        recordTransaction(wallet);
            }
        catch (Exception e){
            System.out.println(e);
        }
        return drawnAmount;
    }

    public void transfer(Long walletId, double amount, Long toWalletId) {
        Wallet fromWallet = walletRepository.findByWalletId(walletId);
        Wallet toWallet = walletRepository.findByWalletId(toWalletId);

        if (fromWallet == null || toWallet == null) {
            throw new WalletNotFoundException("Wallet not found");
        }

        fromWallet.transfer(amount, toWallet);
        //recordTransaction(toWallet);
        recordTransaction(fromWallet);

        walletRepository.save(fromWallet);
        walletRepository.save(toWallet);

    }

    private void recordTransaction(Wallet wallet){
        transactionRepository.save(wallet.getTransactions().get(wallet.getTransactions().size() - 1));
    }
}

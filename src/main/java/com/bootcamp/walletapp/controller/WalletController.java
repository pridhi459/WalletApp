package com.bootcamp.walletapp.controller;

import com.bootcamp.walletapp.exception.InsufficientBalanceException;
import com.bootcamp.walletapp.exception.UserNotFoundException;
import com.bootcamp.walletapp.exception.WalletNotFoundException;
import com.bootcamp.walletapp.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    //Localhost:8080/wallet/deposit  ,@post, prams- (session login / user,password), amount
    //Localhost:8080/wallet/withdraw ,@post, prams- (session login / user,password), amount
    //Localhost:8080/wallet/balance  ,@get
    //Localhost:8080/wallet/transfer ,@post, prams- (session login / user,password), amount, toWalletId


    //Localhost:8080/abc/deposit?amount=100
    @PostMapping("/{walletId}/deposit")
    public ResponseEntity<?> deposit(@PathVariable Long walletId, @RequestParam double amount) {
        try{
            walletService.deposit(walletId, amount);
            return ResponseEntity.ok("Deposit successful");}
        catch (WalletNotFoundException u){
            return ResponseEntity.status(409).body("Wallet does not exist: "+walletId);
        }
        catch (Exception e){
            System.out.println(e+" abc "+e.getCause());
            return ResponseEntity.status(500).body(e.getCause());
        }
    }

    //Localhost:8080/abc/withdrawal?amount=100
    @PostMapping("/{walletId}/withdrawal")
    public ResponseEntity<?> withdrawal(@PathVariable Long walletId, @RequestParam double amount) {
        try{
            walletService.withdraw(walletId, amount);
            return ResponseEntity.ok("Withdrawal successful");}
        catch (WalletNotFoundException u){
            return ResponseEntity.status(409).body("Wallet does not exist: "+ walletId);
        }
        catch (InsufficientBalanceException e){

            return ResponseEntity.status(409).body("Insufficient balance");
        }
        catch (Exception e){
            System.out.println(e+" abc "+e.getCause());
            return ResponseEntity.status(500).body(e.getCause());
        }
    }

    @PostMapping("/{walletId}/transfer")
    public ResponseEntity<?> transfer(@PathVariable Long walletId, @RequestParam double amount, @RequestParam Long toWalletId){
        try{
            walletService.transfer(walletId, amount, toWalletId);
            return ResponseEntity.ok("Transfer successful");}
        catch (WalletNotFoundException u){
            return ResponseEntity.status(409).body("Wallet does not exist: "+ walletId);
        }
        catch (InsufficientBalanceException e){
            return ResponseEntity.status(409).body("Insufficient balance");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getCause());
        }
    }


}

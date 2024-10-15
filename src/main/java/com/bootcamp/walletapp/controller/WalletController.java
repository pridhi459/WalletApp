package com.bootcamp.walletapp.controller;

import com.bootcamp.walletapp.exception.InsufficientBalanceException;
import com.bootcamp.walletapp.exception.UserNotFoundException;
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
    @PostMapping("/{userId}/deposit")
    public ResponseEntity<?> deposit(@PathVariable Long userId, @RequestParam double amount) {
        try{
            walletService.deposit(userId, amount);
            return ResponseEntity.ok("Deposit successful");}
        catch (UserNotFoundException u){
            return ResponseEntity.status(409).body("User does not exist: "+userId);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getCause());
        }
    }

    //Localhost:8080/abc/withdrawal?amount=100
    @PostMapping("/{userId}/withdraw")
    public ResponseEntity<?> withdrawal(@PathVariable Long userId, @RequestParam double amount) {
        try{
            walletService.withdraw(userId, amount);
            return ResponseEntity.ok("Withdrawal successful");}
        catch (UserNotFoundException u){
            return ResponseEntity.status(409).body("User does not exist: "+ userId);
        }
        catch (InsufficientBalanceException e){
            return ResponseEntity.status(409).body("Insufficient balance");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getCause());
        }
    }

    @PostMapping("/{userId}/transfer")
    public ResponseEntity<?> transfer(@PathVariable Long userId, @RequestParam double amount, @RequestParam Long toUserId){
        try{
            walletService.transfer(userId, amount, toUserId);
            return ResponseEntity.ok("Transfer successful");}
        catch (UserNotFoundException u){
            return ResponseEntity.status(409).body("User does not exist: "+ userId);
        }
        catch (InsufficientBalanceException e){
            return ResponseEntity.status(409).body("Insufficient balance");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getCause());
        }
    }


}

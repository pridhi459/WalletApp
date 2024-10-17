package com.bootcamp.walletapp.controller;

import com.bootcamp.walletapp.entity.User;
import com.bootcamp.walletapp.entity.Wallet;
import com.bootcamp.walletapp.exception.InsufficientBalanceException;
import com.bootcamp.walletapp.exception.UserNotFoundException;
import com.bootcamp.walletapp.exception.WalletNotFoundException;
import com.bootcamp.walletapp.repository.UserRepository;
import com.bootcamp.walletapp.repository.WalletRepository;
import com.bootcamp.walletapp.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    //Localhost:8080/wallet/deposit  ,@post, prams- (session login / user,password), amount
    //Localhost:8080/wallet/withdraw ,@post, prams- (session login / user,password), amount
    //Localhost:8080/wallet/balance  ,@get
    //Localhost:8080/wallet/transfer ,@post, prams- (session login / user,password), amount, toWalletId


    //Localhost:8080/abc/deposit?amount=100
    @PostMapping("/{walletId}/deposit")
    public ResponseEntity<?> deposit(@PathVariable Long walletId, @RequestParam double amount) {
        try{
            authenticateUser(walletId);
            walletService.deposit(walletId, amount);
            return ResponseEntity.ok("Deposit successful");}
        catch (WalletNotFoundException u){
            return ResponseEntity.status(404).body("Wallet does not exist: "+walletId);
        }
        catch (InsufficientBalanceException e){
            return ResponseEntity.status(409).body("Insufficient balance");
        }
        catch (AccessDeniedException e){
            return ResponseEntity.status(403).body("Access denied");
        }
        catch (Exception e){
            System.out.println(e+" abc "+e.getCause());
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    //Localhost:8080/wallet/1/withdrawal?amount=100
    @PostMapping("/{walletId}/withdrawal")
    public ResponseEntity<?> withdrawal(@PathVariable Long walletId, @RequestParam double amount) {
        try{
            authenticateUser(walletId);
            walletService.withdraw(walletId, amount);
            return ResponseEntity.ok("Withdrawal successful");}
        catch (WalletNotFoundException u){
            return ResponseEntity.status(404).body("Wallet does not exist: "+ walletId);
        }
        catch (InsufficientBalanceException e){

            return ResponseEntity.status(409).body("Insufficient balance");
        }
        catch (AccessDeniedException e){
            return ResponseEntity.status(403).body("Access denied");
        }
        catch (Exception e){

            return ResponseEntity.status(500).body(e.getCause());
        }
    }

    @PostMapping("/{walletId}/transfer")
    public ResponseEntity<?> transfer(@PathVariable Long walletId, @RequestParam double amount, @RequestParam Long toWalletId){
        try{
            authenticateUser(walletId);
            walletService.transfer(walletId, amount, toWalletId);
            return ResponseEntity.ok("Transfer successful");}
        catch (WalletNotFoundException u){
            return ResponseEntity.status(404).body("Wallet does not exist: "+ walletId);
        }
        catch (InsufficientBalanceException e){
            return ResponseEntity.status(409).body("Insufficient balance");
        }
        catch (AccessDeniedException e){
            return ResponseEntity.status(403).body("Access denied");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getCause());
        }
    }

    private boolean authenticateUser(Long walletId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get the authenticated username
        User user = userRepository.findByUsername(username);

        // Fetch the wallet and check ownership
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        if (!wallet.getUser().getUserId().equals(user.getUserId())) {
            throw new AccessDeniedException("You do not have access to this wallet");
        }

        return true;
    }



}

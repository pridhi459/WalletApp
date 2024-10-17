package com.bootcamp.walletapp.controller;

import com.bootcamp.walletapp.dto.TransactionResponse;
import com.bootcamp.walletapp.entity.Transaction;
import com.bootcamp.walletapp.exception.UserNotFoundException;
import com.bootcamp.walletapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    //Localhost:8080/transaction/history

    @Autowired
    private TransactionService transactionService;


    @GetMapping()
    public ResponseEntity<List<TransactionResponse>> getTransactionHistory(@RequestParam Long walletId) {
        try{
            List<TransactionResponse> transactions= new ArrayList<>();
            for(Transaction t: transactionService.getTransactionHistory(walletId)){
                transactions.add(new TransactionResponse(t));
            }

        return ResponseEntity.ok(transactions);
        }
        catch(UserNotFoundException e){
            return ResponseEntity.status(404).body(null);
                    //ResponseEntity.status(409).body("User does not exists");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }
}

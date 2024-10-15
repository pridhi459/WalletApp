package com.bootcamp.walletapp.controller;

import com.bootcamp.walletapp.entity.Transaction;
import com.bootcamp.walletapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    //Localhost:8080/transaction/history

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/transaction/history")
    public List<Transaction> getTransactionHistory(@RequestParam Long userId) {
        return transactionService.getTransactionHistory(userId);
    }
}

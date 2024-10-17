package com.bootcamp.walletapp.service;

import com.bootcamp.walletapp.repository.TransactionRepository;
import com.bootcamp.walletapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension .class)
class TransactionServiceTest {


    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testGetTransactionHistory() {
        assertNotNull(transactionService.getTransactionHistory(1L));
    }

    @Test
    void testGetTransactionHistoryForInvalidWalletId() {
        assertNull(transactionService.getTransactionHistory(0L));
    }

}
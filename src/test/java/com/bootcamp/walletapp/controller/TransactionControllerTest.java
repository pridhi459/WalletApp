package com.bootcamp.walletapp.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.bootcamp.walletapp.exception.UserNotFoundException;
import com.bootcamp.walletapp.service.TransactionService;
import com.bootcamp.walletapp.service.WalletService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



class TransactionControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TransactionController transactionController;

    @MockBean
    private TransactionService transactionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    void testGetHistoryForWalletId1() throws Exception {
        when(transactionService.getTransactionHistory(2L)).thenReturn(null);
        mockMvc.perform(post("/transaction/history")
                        .param("walletId", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(transactionService).getTransactionHistory(2L);
    }

}
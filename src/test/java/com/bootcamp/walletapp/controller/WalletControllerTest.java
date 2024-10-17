package com.bootcamp.walletapp.controller;


import com.bootcamp.walletapp.exception.UserNotFoundException;
import com.bootcamp.walletapp.service.WalletService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(WalletController.class)
class WalletControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private WalletController walletController;

    @MockBean
    private WalletService walletService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(walletController).build();
    }

//    @Test
//    public void testDepositWithIncorrectWalletId() throws Exception {
//        doThrow(new UserNotFoundException("User not found"))
//                .when(walletService).deposit(1, 100);
//
//        mockMvc.perform(post("/wallet/1/deposit")
//                        .param("amount", "100")
//                      //  .with(httpBasic("user", "password")))
//                .andExpect(status().isConflict())
//                .andExpect(content().string("Wallet does not exist: 1"));
//        verify(walletService, times(1)).deposit(1, 100);
//    }

    @Test
    public void testDeposit100() throws Exception {
        when(walletService.deposit(1, 100)).thenReturn(100.0);

        mockMvc.perform(post("/wallet/1/deposit")
                        .param("amount", "100")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Deposit successful"));
        verify(walletService, times(1)).deposit(1, 100);
    }

    @Test
    public void testWithdrawalFromSufficientBalance() throws Exception {
        when(walletService.withdraw(1L, 100)).thenReturn(100.0);

        mockMvc.perform(post("/wallet/1/withdrawal")
                        .param("amount", "100")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Withdrawal successful"));
        verify(walletService, times(1)).withdraw(1L, 100);

    }


}
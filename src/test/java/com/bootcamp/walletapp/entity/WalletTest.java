package com.bootcamp.walletapp.entity;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class WalletTest {

    @Test
    void testWalletCreation() {
        Wallet wallet = new Wallet();
        assertNotNull(wallet);
    }
}

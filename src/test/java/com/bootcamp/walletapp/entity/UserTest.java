package com.bootcamp.walletapp.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
        User user = new User("prihdi","pridhi");
        User secondUser = new User("prihdi2","pridhi2");
        assertNotNull(user);
    }



}
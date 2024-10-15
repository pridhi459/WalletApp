package com.bootcamp.walletapp.service;

import com.bootcamp.walletapp.entity.User;
import com.bootcamp.walletapp.exception.UserAlreadyExistsException;
import com.bootcamp.walletapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testRegisterUser() {
        User user = new User("testUser","password");

        when(userRepository.save(any(User.class))).thenReturn(user);
        User registeredUser = userService.register("testuser", "password");

        assertNotNull(registeredUser);
        assertEquals("testUser", registeredUser.getUsername());
    }

    @Test
    void testDuplicateUser() {
        User user = new User("testUser","password");

        when(userRepository.findByUsername("testUser")).thenReturn(user);
        assertThrows(UserAlreadyExistsException.class, () -> userService.register("testUser","password"));
    }
}


//package com.bootcamp.walletapp.controller;
//
//import com.bootcamp.walletapp.entity.User;
//import com.bootcamp.walletapp.exception.UserAlreadyExistsException;
//import com.bootcamp.walletapp.exception.UserNotFoundException;
//import com.bootcamp.walletapp.service.UserService;
//import org.junit.jupiter.api.*;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private UserController userController;
//
//    @MockBean
//    private UserService userService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }
//
//    @Test
//    public void testRegisterUser() throws Exception {
//        User user = new User("testuser", "password");
//
//        when(userService.register("testuser", "password")).thenReturn(user);
//        mockMvc.perform(post("/register")
//                        .param("username", "sai")
//                        .param("password", "password456")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        verify(userService).register("testuser", "password");
//    }
//
//
//    @Test
//    public void testRegisterUserUsernameAlreadyExists() throws Exception {
//        User user = new User("User", "User1");
//        doThrow(new UserAlreadyExistsException("Username already exists"))
//                .when(userService).register("User", "User1");
//
//        mockMvc.perform(post("/register")
//                        .param("username", "User")
//                        .param("password", "User1")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().isConflict())
//                .andExpect(content().string("User already exists"));
//        verify(userService, times(1)).register("User", "User1");
//    }
//
//    @Test
//    public void testDepositWithIncorrectUsername() throws Exception {
//        doThrow(new UserNotFoundException("User not found"))
//                .when(userService).deposit("pridhi", 100);
//
//        mockMvc.perform(post("/pridhi/deposit")
//                        .param("amount", "100")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().isConflict())
//                .andExpect(content().string("User does not exist: pridhi"));
//        verify(userService, times(1)).deposit("pridhi", 100);
//    }
//
//    @Test
//    public void testDeposit100() throws Exception {
//        when(userService.deposit("pridhi", 100)).thenReturn(100.0);
//
//        mockMvc.perform(post("/pridhi/deposit")
//                        .param("amount", "100")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Deposit successful"));
//        verify(userService, times(1)).deposit("pridhi", 100);
//    }
//
//    @Test
//    public void testWithdrawalFromSufficientBalance() throws Exception {
//        when(userService.withdraw("pridhi", 100)).thenReturn(100.0);
//
//        mockMvc.perform(post("/pridhi/withdraw")
//                        .param("amount", "100")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Withdrawal successful"));
//        verify(userService, times(1)).withdraw("pridhi", 100);
//
//    }
//
//}
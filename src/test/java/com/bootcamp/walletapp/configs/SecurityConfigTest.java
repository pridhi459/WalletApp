//package com.bootcamp.walletapp.configs;
//
//import com.bootcamp.walletapp.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//
//@WebMvcTest(SecurityConfig.class)
//class SecurityConfigTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @BeforeEach
//    public void setUp() {
//        // Set up if needed
//    }
//
//    @Test
//    @WithMockUser(username = "user", password = "password", roles = "USER")
//    public void testSecureEndpointWithAuth() throws Exception {
//        mockMvc.perform(post("/api/secure-endpoint")
//                        .with(user("user").password("password").roles("USER")))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Success!"));
//    }
//
//    @Test
//    public void testSecureEndpointWithoutAuth() throws Exception {
//        mockMvc.perform(post("/api/secure-endpoint"))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    public void testPublicEndpoint() throws Exception {
//        mockMvc.perform(post("/user/register"))
//                .andExpect(status().isOk());
//    }
//}
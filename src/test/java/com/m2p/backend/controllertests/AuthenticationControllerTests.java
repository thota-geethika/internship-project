package com.m2p.backend.controllertests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2p.backend.authentication.controller.AuthenticationController;
import com.m2p.backend.authentication.service.AuthenticationService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(AuthenticationController.class)
@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTests {

    @Nested
    class LoginController{

        @MockBean
        private AuthenticationService authenticationService;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void toCheckWhetherUserNameOrEmailAndPasswordIsPresentOnTheDatabase() throws Exception{

            String username = "naveenmr13";
            String password = "Naveen@01";

            mockMvc.perform(MockMvcRequestBuilders.get("/api/authenticate")
                            .param("user",username)
                            .param("password",password))
                            .andExpect(status().isOk());
        }

    }
}

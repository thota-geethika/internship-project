package com.m2p.backend.controllertests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2p.backend.authentication.controller.AuthenticationController;
import com.m2p.backend.authentication.repository.AuthenticationRepository;
import com.m2p.backend.authentication.service.AuthenticationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig({AuthenticationService.class,AuthenticationRepository.class, EntityManagerFactory.class})
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

            String userName = "naveenmr13";

            String password = "Naveen@01";

            mockMvc.perform(MockMvcRequestBuilders.get("/api/authenticate")
                            .param("user",userName)
                            .param("password",password))
                            .andExpect(status().isOk());

        }

    }
}

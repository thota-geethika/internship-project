package com.m2p.backend.controllertests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2p.backend.authentication.controller.AuthenticationController;
import com.m2p.backend.authentication.model.UserDetails;
import com.m2p.backend.authentication.service.AuthenticationService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(AuthenticationService.class)
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

            Mockito.when(authenticationService.userIsValid(username,password)).thenReturn(true);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/authenticate")
                            .param("user",username)
                            .param("password",password))
                            .andExpect(status().isOk())
                            .andDo(print());
        }

        @Test
        void toCheckWhetherUserNameOrEmailAndPasswordIsNotPresentOnTheDatabase() throws Exception{

            String username = "naveenmr13";
            String password = "Naveen@01";

            Mockito.when(authenticationService.userIsValid(username,password)).thenReturn(false);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/authenticate")
                            .param("user",username)
                            .param("password",password))
                            .andExpect(status().isOk());
        }

        @Test
        void toCheckWhetherUserDetailsIsStoredInTheDatabase() throws Exception {
            UserDetails userDetails = new UserDetails(1,"Vignesh","Vigneshmanikam2001@gmail.com","vicky7");
            String json = objectMapper.writer().writeValueAsString(userDetails);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                            .contentType(MediaType.APPLICATION_JSON).content(json))
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    class RegisterController{
        @MockBean
        private AuthenticationService authenticationService;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void toCheckIfAUserNameExistsInTheDatabaseOrNot() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/check-user").param("name","Vignesh"))
                    .andExpect(status().isOk());
        }

        @Test
        void toCheckIfEmailExistsInTheDatabaseOrNot() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/check-email").param("email","vigneshmanikam2001@gmail.com"))
                    .andExpect(status().isOk());
        }

        @Test
        void toCheckWhetherUserDetailsIsStoredInTheDatabase() throws Exception {
            UserDetails userDetails = new UserDetails(1,"Vignesh","Vigneshmanikam2001@gmail.com","vicky7");
            String json = objectMapper.writer().writeValueAsString(userDetails);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                            .contentType(MediaType.APPLICATION_JSON).content(json))
                    .andExpect(status().isCreated());
        }
    }
}

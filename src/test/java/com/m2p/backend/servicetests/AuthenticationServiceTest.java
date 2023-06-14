package com.m2p.backend.servicetests;

import com.m2p.backend.authentication.repository.AuthenticationRepository;
import com.m2p.backend.authentication.service.AuthenticationService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Nested
    class LoginService{

        @InjectMocks
        private AuthenticationService authenticationService;

        @Mock
        private AuthenticationRepository authenticationRepository;

        @Test
        void toCheckTheUserNameIsValidOrNot(){

            String userName = "naveenmr13";

            String password = "Naveen@01";

            Mockito.when(authenticationRepository.validateUserName(userName,password)).thenReturn(1);

            Boolean validUser = authenticationService.userIsValid(userName,password);

            assertThat(validUser).isEqualTo(true);

            verify(authenticationRepository).validateUserName(userName,password);


        }

        @Test
        void toCheckTheEmailIsValidOrNot(){

            String email = "naveenmr13@gmail.com";

            String password = "Naveen@01";

            Mockito.when(authenticationRepository.validateEmail(email,password)).thenReturn(1);

            Boolean validUser = authenticationService.userIsValid(email,password);

            assertThat(validUser).isEqualTo(true);

            verify(authenticationRepository).validateEmail(email,password);

        }

    }
}

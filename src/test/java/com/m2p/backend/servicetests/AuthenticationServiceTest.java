package com.m2p.backend.servicetests;

import com.m2p.backend.authentication.model.UserDetails;
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
import org.springframework.security.crypto.bcrypt.BCrypt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

        @Nested
        class RegisterService
        {
            @InjectMocks
            private AuthenticationService authenticationService;

            @Mock
            private AuthenticationRepository authenticationRepository;
            @Test
            void toCheckUserNameIsAvailable() {
                Mockito.when(authenticationRepository.checkUserName("sruthi")).thenReturn(0);
                boolean availableUser = authenticationService.checkUserAvailability("sruthi");
                assertThat(availableUser).isEqualTo(true);
                verify(authenticationRepository).checkUserName("sruthi");
            }
            @Test
            void toCheckUserNameIsNotAvailable() {
                Mockito.when(authenticationRepository.checkUserName("Vicky7")).thenReturn(1);
                boolean availableUser = authenticationService.checkUserAvailability("Vicky7");
                assertThat(availableUser).isEqualTo(false);
                verify(authenticationRepository).checkUserName("Vicky7");
            }

            @Test
            void toCheckEmailIsAvailable() {
                Mockito.when(authenticationRepository.checkEmail("sruthiabhi@gmail.com")).thenReturn(0);
                boolean availableEmail = authenticationService.checkEmailAvailability("sruthiabhi@gmail.com");
                assertThat(availableEmail).isEqualTo(true);
                verify(authenticationRepository).checkEmail("sruthiabhi@gmail.com");
            }
            @Test
            void toCheckEmailIsNotAvailable() {
                Mockito.when(authenticationRepository.checkEmail("vigneshmanikam2001@gmail.com")).thenReturn(1);
                boolean availableEmail = authenticationService.checkEmailAvailability("vigneshmanikam2001@gmail.com");
                assertThat(availableEmail).isEqualTo(false);
                verify(authenticationRepository).checkEmail("vigneshmanikam2001@gmail.com");
            }

            @Test
            void toCheckWhetherUserDetailsAreGettingStored(){
                UserDetails details = new UserDetails(7,"VigneshM","vigneshmanikavasagam17@gmail.com","Vignesh17");
                Mockito.when(authenticationRepository.save(details)).thenReturn(details);
                authenticationService.createUser(details);
                Mockito.when(authenticationRepository.checkUserName("VigneshM")).thenReturn(1);
                boolean result = authenticationService.checkUserAvailability("VigneshM");
                assertThat(result).isEqualTo(false);
            }
        }

        @Nested
        class LoginServiceTests{
            @InjectMocks
            private AuthenticationService authenticationService;

            @Mock
            private AuthenticationRepository authenticationRepository;

            @Test
            void itShouldReturnTrueWhenValidUserAndPasswordIsPassed(){

                String user = "admin";

                String encryptedPassword = "YWRtaW4NCg==";

                String decryptedPassword = "admin";

                String hashedPassword = BCrypt.hashpw(decryptedPassword, BCrypt.gensalt());

                when(authenticationRepository.validUserAsPassword(user)).thenReturn(hashedPassword);

                authenticationService.userIsValid(user, encryptedPassword);

                assertTrue(BCrypt.checkpw(decryptedPassword,hashedPassword));
            }

            @Test
            void itShouldReturnFalseWhenAnInvalidUserIsPassed(){
                String user = "admin";
                String encryptedPassword = "YWRtaW4NCg==";

                when(authenticationRepository.validUserAsPassword(user)).thenReturn(null);

                Boolean value = authenticationService.userIsValid(user, encryptedPassword);

                assertFalse(value);
            }
        }

    }

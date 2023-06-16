package com.m2p.backend.servicetests;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

//    @Nested
//    class LoginService{
//
//        @InjectMocks
//        private AuthenticationService authenticationService;
//
//        @Mock
//        private AuthenticationRepository authenticationRepository;
//
//        @Test
//        void toCheckTheUserNameIsValidOrNot(){
//
//            String userName = "naveenmr13";
//
//            String password = "Naveen@01";
//
//            Mockito.when(authenticationRepository.validateUserName(userName,password)).thenReturn(true);
//
//            Boolean validUser = authenticationService.userIsValid(userName,password);
//
//            assertThat(validUser).isEqualTo(true);
//
//            verify(authenticationRepository).validateUserName(userName,password);
//
//
//        }
//
//        @Test
//        void toCheckTheEmailIsValidOrNot(){
//
//            String email = "naveenmr13@gmail.com";
//
//            String password = "Naveen@01";
//
//            Mockito.when(authenticationRepository.validateEmail(email,password)).thenReturn(true);
//
//            Boolean validUser = authenticationService.userIsValid(email,password);
//
//            assertThat(validUser).isEqualTo(true);
//
//            verify(authenticationRepository).validateEmail(email,password);
//
//        }

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
                UserDetails details = new UserDetails(7,"VigneshM","vigneshmanikavasagam17@gmail.com","VmlnbmVzaDE3");
                Mockito.when(authenticationRepository.save(details)).thenReturn(details);

                String encryptedPwdFromFrontEnd = details.getPassword();
                String decryptPwdFromFrontEnd = "Vignesh17";
                String encryptPwdForBackend = "2a$10$xuEdZfLhMhSVtYXvjjtG7.WhfK4HDGYxeWgMzqzaS8C46esw4EYfK";


                BCryptPasswordEncoder bCrypt = mock(BCryptPasswordEncoder.class);
                Mockito.when(bCrypt.encode(decryptPwdFromFrontEnd)).thenReturn(encryptPwdForBackend);

                String resultOfDecryptionFrontEnd = authenticationService.decryptPassword(encryptedPwdFromFrontEnd);
                String resultOfEncryptionBackend = bCrypt.encode(decryptPwdFromFrontEnd);

                assertThat(decryptPwdFromFrontEnd).isEqualTo(resultOfDecryptionFrontEnd);
                assertThat(encryptPwdForBackend).isEqualTo(resultOfEncryptionBackend);
                authenticationService.createUser(details);
            }
        }
    }
//}

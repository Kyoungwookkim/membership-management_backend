package app.membershipmanagement_backend;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.account.controller.AccountRestController;
import app.membershipmanagement_backend.api.account.dto.UserRegisterDto;
import app.membershipmanagement_backend.api.account.repository.AccountRepository;
import app.membershipmanagement_backend.api.account.service.UserAccountService;
import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.global.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class AccountTest {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistersuccess() throws Exception {

        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUserId("test001");
        userRegisterDto.setUserPassword("Test@1234");
        userRegisterDto.setUserName("John Doe");
        userRegisterDto.setUserProfileNickname("johnny");
        userRegisterDto.setUserPhoneNumber("1234567890");
        userRegisterDto.setUserAddress("123 Main Street");

        mockMvc.perform(post("/api/account/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRegisterDto)))
                .andExpect(status().isOk());

        User user = accountRepository.findByUserId("test001");
        assertEquals(userRegisterDto.getUserId(), user.getUserId());

    }

    @Test
    void testRegisterIdDuplicate() throws Exception {
       /* // 먼저 동일한 아이디를 가진 사용자를 생성합니다.
        User existingUser = new User();
        existingUser.setUserId("mzd021");
        existingUser.setUserPassword("Test@1234");
        existingUser.setUserName("기존 사용자");
        accountRepository.save(existingUser);


        // 이제 동일한 아이디로 새로운 사용자 등록을 시도합니다.*/
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUserId("mzd01");
        userRegisterDto.setUserPassword("Test@5678");
        userRegisterDto.setUserName("John Doe");
        userRegisterDto.setUserProfileNickname("johnny");
        userRegisterDto.setUserPhoneNumber("9876543210");
        userRegisterDto.setUserAddress("456 Main Street");


        Assertions.assertThrows(CustomException.class, () -> {
            mockMvc.perform(post("/api/account/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(userRegisterDto)))
                    .andExpect(status().isBadRequest());  // 400 Bad Request 상태 코드를 예상
        });

    }

}


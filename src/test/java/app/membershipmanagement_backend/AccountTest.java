package app.membershipmanagement_backend;

import app.membershipmanagement_backend.api.account.dto.UserDeleteDto;
import app.membershipmanagement_backend.api.account.dto.UserRegisterDto;
import app.membershipmanagement_backend.api.repository.AccountRepository;
import app.membershipmanagement_backend.api.repository.UserProfileRepository;
import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.entity.UserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    private UserProfileRepository userProfileRepository;

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

        mockMvc.perform(post("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRegisterDto)))
                .andExpect(status().isOk());

        User user = accountRepository.findByUserId("test001");
        assertEquals(userRegisterDto.getUserId(), user.getUserId());

    }

    @Test
    void testRegisterIdDuplicate() throws Exception {

        User existingUser = new User();
        UserProfile existingUserProfile = new UserProfile();
        existingUser.setUserId("mzd021");
        existingUser.setUserPassword("Test@1234");
        existingUser.setUserName("기존 사용자");
        accountRepository.save(existingUser);


        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUserId("mzd021");
        userRegisterDto.setUserPassword("Test@5678");
        userRegisterDto.setUserName("새로운 사용자");
        userRegisterDto.setUserProfileNickname("김반장");
        userRegisterDto.setUserPhoneNumber("9876543210");
        userRegisterDto.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");



                mockMvc.perform(post("/api/account")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(userRegisterDto)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.success").value(false))
                        .andExpect(jsonPath("$.message").value("중복된 아이디가 있습니다."));


    }

    @Test
    void testRegisterDelete() throws Exception {

        User existingUser = new User();
        existingUser.setUserId("mzd021");
        existingUser.setUserPassword("Test@1234");
        existingUser.setUserName("사용자");
        accountRepository.save(existingUser);

        UserProfile existingUserProfile = new UserProfile();
        existingUserProfile.setUser(existingUser);
        existingUserProfile.setUserProfileNickname("기존닉네임");
        existingUserProfile.setUserPhoneNumber("01012345678");
        existingUserProfile.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");
        existingUserProfile.setUserMainProfile(1);
        userProfileRepository.save(existingUserProfile);

        UserDeleteDto userDeleteDto = new UserDeleteDto();
        userDeleteDto.setUserId("mzd021");

        mockMvc.perform(delete("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDeleteDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("사용자 및 관련 프로필 삭제 성공"));

    }

    @Test
    void testRegisterDeleteWithUserIdError() throws Exception {

        User existingUser = new User();
        existingUser.setUserId("mzd021");
        existingUser.setUserPassword("Test@1234");
        existingUser.setUserName("사용자");
        accountRepository.save(existingUser);

        UserProfile existingUserProfile = new UserProfile();
        existingUserProfile.setUser(existingUser);
        existingUserProfile.setUserProfileNickname("기존닉네임");
        existingUserProfile.setUserPhoneNumber("01012345678");
        existingUserProfile.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");
        existingUserProfile.setUserMainProfile(1);
        userProfileRepository.save(existingUserProfile);

        UserDeleteDto userDeleteDto = new UserDeleteDto();
        userDeleteDto.setUserId("mzd020");

        mockMvc.perform(delete("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDeleteDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("사용자를 찾을 수 없습니다."));

    }




}


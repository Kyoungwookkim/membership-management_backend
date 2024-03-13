package app.membershipmanagement_backend;

import app.membershipmanagement_backend.api.repository.AccountRepository;
import app.membershipmanagement_backend.api.repository.UserProfileRepository;
import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.entity.UserProfile;
import app.membershipmanagement_backend.api.user.dto.UserProfileDeleteDto;
import app.membershipmanagement_backend.api.user.dto.UserProfileEditDto;
import app.membershipmanagement_backend.api.user.dto.UserProfileRegisterDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class UserProfileTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistersuccess() throws Exception {

        User existingUser = new User();
        existingUser.setUserId("mzd021");
        existingUser.setUserPassword("Test@1234");
        existingUser.setUserName("기존 사용자");
        accountRepository.save(existingUser);

        UserProfile existingUserProfile = new UserProfile();
        existingUserProfile.setUser(existingUser);
        existingUserProfile.setUserProfileNickname("황치치");
        existingUserProfile.setUserPhoneNumber("01037805837");
        existingUserProfile.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");
        userProfileRepository.save(existingUserProfile);


        UserProfileRegisterDto userProfileRegisterDto = new UserProfileRegisterDto();
        userProfileRegisterDto.setUserId("mzd021");
        userProfileRegisterDto.setUserProfileNickname("김반장");
        userProfileRegisterDto.setUserPhoneNumber("01037805837");
        userProfileRegisterDto.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");


        mockMvc.perform(post("/api/user/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userProfileRegisterDto)))
                .andExpect(status().isOk());


    }


    @Test
    void testProfileRegisterWithDuplicateNickname() throws Exception {

        User existingUser = new User();
        existingUser.setUserId("mzd021");
        existingUser.setUserPassword("Test@1234");
        existingUser.setUserName("기존 사용자");
        accountRepository.save(existingUser);


        UserProfile existingUserProfile = new UserProfile();
        existingUserProfile.setUser(existingUser);
        existingUserProfile.setUserProfileNickname("johnny");
        existingUserProfile.setUserPhoneNumber("9876543210");
        existingUserProfile.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");
        userProfileRepository.save(existingUserProfile);


        UserProfileRegisterDto userProfileRegisterDto = new UserProfileRegisterDto();
        userProfileRegisterDto.setUserId("mzd021");
        userProfileRegisterDto.setUserProfileNickname("johnny");
        userProfileRegisterDto.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");


        mockMvc.perform(post("/api/user/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userProfileRegisterDto)))
                .andExpect(status().isOk()) // 예외 처리에 맞게 수정
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("이미 사용 중인 프로필 이름입니다."));
    }

    @Test
    void testProfileEdit() throws Exception {
        User existingUser = new User();
        existingUser.setUserId("mzd021");
        existingUser.setUserPassword("Test@1234");
        existingUser.setUserName("기존 사용자");
        accountRepository.save(existingUser);

        UserProfile existingUserProfile = new UserProfile();
        existingUserProfile.setUser(existingUser);
        existingUserProfile.setUserProfileNum(30L);
        existingUserProfile.setUserProfileNickname("기존닉네임");
        existingUserProfile.setUserPhoneNumber("01012345678");
        existingUserProfile.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");
        userProfileRepository.save(existingUserProfile);


        UserProfileEditDto userProfileEditDto = new UserProfileEditDto();
        userProfileEditDto.setUserId("mzd021");
        userProfileEditDto.setUserProfileNum(existingUserProfile.getUserProfileNum());
        userProfileEditDto.setUserProfileNickname("새로운닉네임");
        userProfileEditDto.setUserPhoneNumber("01012345678");
        userProfileEditDto.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");

        mockMvc.perform(put("/api/user/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userProfileEditDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("프로필 업데이트 성공"));
    }

    @Test
    void testProfileEditWithDuplicateNickname() throws Exception {
        User existingUser = new User();
        existingUser.setUserId("mzd021");
        existingUser.setUserPassword("Test@1234");
        existingUser.setUserName("기존 사용자");
        accountRepository.save(existingUser);

        UserProfile existingUserProfile = new UserProfile();
        existingUserProfile.setUser(existingUser);
        existingUserProfile.setUserProfileNum(30L);
        existingUserProfile.setUserProfileNickname("기존닉네임");
        existingUserProfile.setUserPhoneNumber("01012345678");
        existingUserProfile.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");
        userProfileRepository.save(existingUserProfile);

        // 테스트용 UserProfileEditDto 생성
        UserProfileEditDto userProfileEditDto = new UserProfileEditDto();
        userProfileEditDto.setUserId("mzd021");
        userProfileEditDto.setUserProfileNum(existingUserProfile.getUserProfileNum());
        userProfileEditDto.setUserProfileNickname("기존닉네임");
        userProfileEditDto.setUserPhoneNumber("01012345678");
        userProfileEditDto.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");

        mockMvc.perform(put("/api/user/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userProfileEditDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("이미 사용 중인 프로필 이름입니다."));
    }

    @Test
    void testProfileDelete() throws Exception {
        User existingUser = new User();
        existingUser.setUserId("mzd021");
        existingUser.setUserPassword("Test@1234");
        existingUser.setUserName("기존사용자");
        accountRepository.save(existingUser);

        UserProfile existingUserProfile = new UserProfile();
        existingUserProfile.setUser(existingUser);
        existingUserProfile.setUserProfileNum(30L);
        existingUserProfile.setUserProfileNickname("기존닉네임");
        existingUserProfile.setUserPhoneNumber("01012345678");
        existingUserProfile.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");
        existingUserProfile.setUserMainProfile(0);
        userProfileRepository.save(existingUserProfile);

        // 테스트용 UserProfileEditDto 생성
        UserProfileDeleteDto userProfileDeleteDto = new UserProfileDeleteDto();
        userProfileDeleteDto.setUserId("mzd021");
        userProfileDeleteDto.setUserProfileNickname("기존닉네임");


        mockMvc.perform(delete("/api/user/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userProfileDeleteDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("프로필 삭제 성공"));
    }


    @Test
    void testProfileDeleteWithDuplicateNickname() throws Exception {
        User existingUser = new User();
        existingUser.setUserId("mzd021");
        existingUser.setUserPassword("Test@1234");
        existingUser.setUserName("사용자");
        accountRepository.save(existingUser);

        UserProfile existingUserProfile = new UserProfile();
        existingUserProfile.setUser(existingUser);
        existingUserProfile.setUserProfileNum(30L);
        existingUserProfile.setUserProfileNickname("기존닉네임");
        existingUserProfile.setUserPhoneNumber("01012345678");
        existingUserProfile.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");
        existingUserProfile.setUserMainProfile(1);
        userProfileRepository.save(existingUserProfile);

        // 테스트용 UserProfileEditDto 생성
        UserProfileDeleteDto userProfileDeleteDto = new UserProfileDeleteDto();
        userProfileDeleteDto.setUserId("mzd021");
        userProfileDeleteDto.setUserProfileNickname("기존닉네임");


        mockMvc.perform(delete("/api/user/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userProfileDeleteDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("메인 프로필은 삭제할 수 없습니다."));
    }

    @Test
    void testProfileDeleteWithNickname() throws Exception {
        User existingUser = new User();
        existingUser.setUserId("mzd021");
        existingUser.setUserPassword("Test@1234");
        existingUser.setUserName("사용자");
        accountRepository.save(existingUser);

        UserProfile existingUserProfile = new UserProfile();
        existingUserProfile.setUser(existingUser);
        existingUserProfile.setUserProfileNum(30L);
        existingUserProfile.setUserProfileNickname("기존닉네임");
        existingUserProfile.setUserPhoneNumber("01012345678");
        existingUserProfile.setUserAddress("메가존빌딩, 46 논현로85길 강남구 서울특별시");
        existingUserProfile.setUserMainProfile(1);
        userProfileRepository.save(existingUserProfile);

        // 테스트용 UserProfileEditDto 생성
        UserProfileDeleteDto userProfileDeleteDto = new UserProfileDeleteDto();
        userProfileDeleteDto.setUserId("mzd021");
        userProfileDeleteDto.setUserProfileNickname("틀린닉네임");


        mockMvc.perform(delete("/api/user/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userProfileDeleteDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("프로필을 찾을 수 없습니다."));
    }
}


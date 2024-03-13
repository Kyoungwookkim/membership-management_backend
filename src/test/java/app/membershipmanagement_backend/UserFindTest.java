package app.membershipmanagement_backend;

import app.membershipmanagement_backend.api.repository.AccountRepository;
import app.membershipmanagement_backend.api.repository.UserProfileRepository;
import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.entity.UserProfile;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class UserFindTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetUserList() throws Exception {

        for (int i = 1; i <= 3; i++) {
            User User = new User();
            User.setUserId("user" + i);
            User.setUserPassword("Test@1234");
            User.setUserName("User " + i);
            accountRepository.save(User);

            UserProfile testUserProfile = new UserProfile();
            testUserProfile.setUser(User);
            testUserProfile.setUserProfileNickname("Nickname" + i);
            testUserProfile.setUserPhoneNumber("0101234" + (1000 + i));
            testUserProfile.setUserAddress("Address" + i);
            testUserProfile.setUserMainProfile(1);
            userProfileRepository.save(testUserProfile);
        }


        mockMvc.perform(get("/api/search/user/list")
                        .param("keyword", "")
                        .param("size", "")
                        .param("page", ""))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(3))
                .andExpect(jsonPath("$.content[0].userId").value("user1"))
                .andExpect(jsonPath("$.content[0].userName").value("User 1"))
                .andExpect(jsonPath("$.content[0].userNickName").value("Nickname1"))
                .andExpect(jsonPath("$.content[0].userPhoneNumber").value("01012341001"))
                .andExpect(jsonPath("$.content[0].userAddress").value("Address1"))
                .andExpect(jsonPath("$.content[1].userId").value("user2"))
                .andExpect(jsonPath("$.content[1].userName").value("User 2"))
                .andExpect(jsonPath("$.content[1].userNickName").value("Nickname2"))
                .andExpect(jsonPath("$.content[1].userPhoneNumber").value("01012341002"))
                .andExpect(jsonPath("$.content[1].userAddress").value("Address2"))
                .andExpect(jsonPath("$.content[2].userId").value("user3"))
                .andExpect(jsonPath("$.content[2].userName").value("User 3"))
                .andExpect(jsonPath("$.content[2].userNickName").value("Nickname3"))
                .andExpect(jsonPath("$.content[2].userPhoneNumber").value("01012341003"))
                .andExpect(jsonPath("$.content[2].userAddress").value("Address3"));

    }


    @Test
    void testGetUserListPagination() throws Exception {

        for (int i = 1; i <= 8; i++) {
            User User = new User();
            User.setUserId("user" + i);
            User.setUserPassword("Test@1234");
            User.setUserName("User " + i);
            accountRepository.save(User);

            UserProfile testUserProfile = new UserProfile();
            testUserProfile.setUser(User);
            testUserProfile.setUserProfileNickname("Nickname" + i);
            testUserProfile.setUserPhoneNumber("0101234" + (1000 + i));
            testUserProfile.setUserAddress("Address" + i);
            testUserProfile.setUserMainProfile(1);
            userProfileRepository.save(testUserProfile);
        }


        mockMvc.perform(get("/api/search/user/list")
                        .param("keyword", "")
                        .param("size", "2")
                        .param("page", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].userId").value("user5"))
                .andExpect(jsonPath("$.content[0].userName").value("User 5"))
                .andExpect(jsonPath("$.content[0].userNickName").value("Nickname5"))
                .andExpect(jsonPath("$.content[0].userPhoneNumber").value("01012341005"))
                .andExpect(jsonPath("$.content[0].userAddress").value("Address5"))
                .andExpect(jsonPath("$.content[1].userId").value("user6"))
                .andExpect(jsonPath("$.content[1].userName").value("User 6"))
                .andExpect(jsonPath("$.content[1].userNickName").value("Nickname6"))
                .andExpect(jsonPath("$.content[1].userPhoneNumber").value("01012341006"))
                .andExpect(jsonPath("$.content[1].userAddress").value("Address6"));

    }


    @Test
    void testGetUserSearchList() throws Exception {

        for (int i = 1; i <= 8; i++) {
            User User = new User();
            User.setUserId("user" + i);
            User.setUserPassword("Test@1234");
            User.setUserName("User " + i);
            accountRepository.save(User);

            UserProfile testUserProfile = new UserProfile();
            testUserProfile.setUser(User);
            testUserProfile.setUserProfileNickname("Nickname" + i);
            testUserProfile.setUserPhoneNumber("0101234" + (1000 + i));
            testUserProfile.setUserAddress("Address" + i);
            testUserProfile.setUserMainProfile(1);
            userProfileRepository.save(testUserProfile);
        }


        mockMvc.perform(get("/api/search/user/list")
                        .param("keyword", "8")
                        .param("size", "")
                        .param("page", ""))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].userId").value("user8"))
                .andExpect(jsonPath("$.content[0].userName").value("User 8"))
                .andExpect(jsonPath("$.content[0].userNickName").value("Nickname8"))
                .andExpect(jsonPath("$.content[0].userPhoneNumber").value("01012341008"))
                .andExpect(jsonPath("$.content[0].userAddress").value("Address8"));

    }

    @Test
    void testGetUserDetail() throws Exception {

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            User user = new User();
            user.setUserId("user" + i);
            user.setUserPassword("Test@1234");
            user.setUserName("User " + i);
            users.add(accountRepository.save(user));

            UserProfile userProfile = new UserProfile();
            userProfile.setUser(user);
            userProfile.setUserProfileNickname("Nickname" + i);
            userProfile.setUserPhoneNumber("0101234" + (1000 + i));
            userProfile.setUserAddress("Address" + i);
            userProfile.setUserMainProfile(1);
            userProfileRepository.save(userProfile);
            for (int k = 2; k <= 3; k++) {
                UserProfile userProfile2 = new UserProfile();
                userProfile2.setUser(user);
                userProfile2.setUserProfileNickname("Nickname" + k);
                userProfile2.setUserPhoneNumber("0101234" + (1000 + k));
                userProfile2.setUserAddress("Address" + k);
                userProfile2.setUserMainProfile(0);
                userProfileRepository.save(userProfile2);
            }
        }

        Long userNumToRetrieve = users.get(0).getUserNum();

        mockMvc.perform(get("/api/search/user/list/detail")
                        .param("userNum", String.valueOf(userNumToRetrieve)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.findUserProfileDetailDtoList.length()").value(3))
                .andExpect(jsonPath("$.userId").value("user1"))
                .andExpect(jsonPath("$.userName").value("User 1"))
                .andExpect(jsonPath("$.findUserProfileDetailDtoList[0].userProfileNickname").value("Nickname1"))
                .andExpect(jsonPath("$.findUserProfileDetailDtoList[0].userPhoneNumber").value("01012341001"))
                .andExpect(jsonPath("$.findUserProfileDetailDtoList[0].userAddress").value("Address1"))
                .andExpect(jsonPath("$.findUserProfileDetailDtoList[1].userProfileNickname").value("Nickname2"))
                .andExpect(jsonPath("$.findUserProfileDetailDtoList[1].userPhoneNumber").value("01012341002"))
                .andExpect(jsonPath("$.findUserProfileDetailDtoList[1].userAddress").value("Address2"))
                .andExpect(jsonPath("$.findUserProfileDetailDtoList[2].userProfileNickname").value("Nickname3"))
                .andExpect(jsonPath("$.findUserProfileDetailDtoList[2].userPhoneNumber").value("01012341003"))
                .andExpect(jsonPath("$.findUserProfileDetailDtoList[2].userAddress").value("Address3"));

    }

}

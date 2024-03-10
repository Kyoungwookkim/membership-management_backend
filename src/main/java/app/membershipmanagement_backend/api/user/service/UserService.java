package app.membershipmanagement_backend.api.user.service;


import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.account.repository.AccountRepository;
import app.membershipmanagement_backend.api.account.repository.UserProfileRepository;
import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.entity.UserProfile;
import app.membershipmanagement_backend.api.user.dto.UserDeleteDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AccountRepository accountRepository;
    private final UserProfileRepository userProfileRepository;

        @Transactional
    public DefaultResultDto deleteUser(UserDeleteDto userDeleteDto) {
        // userNum을 사용하여 해당 user를 찾습니다.
        User user = accountRepository.findByUserId(userDeleteDto.getUserId());

        // user가 없는 경우
        if (user == null) {
            return DefaultResultDto.builder().success(false).message("사용자를 찾을 수 없습니다.").build();
        }

        // 해당 user의 모든 프로필을 찾습니다.
        List<UserProfile> userProfiles = userProfileRepository.findByUser(user);

        // user의 모든 프로필을 삭제합니다.
        userProfileRepository.deleteAll(userProfiles);

        // user를 삭제합니다.
        accountRepository.delete(user);

        return DefaultResultDto.builder().success(true).message("사용자 및 관련 프로필 삭제 성공").build();
    }



}

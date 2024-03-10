package app.membershipmanagement_backend.api.user.service;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.account.repository.AccountRepository;
import app.membershipmanagement_backend.api.account.repository.UserProfileRepository;
import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.entity.UserProfile;
import app.membershipmanagement_backend.api.user.dto.UserProfileRegisterDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final AccountRepository accountRepository;
    private final UserProfileRepository userProfileRepository;
    @Transactional
    public DefaultResultDto profileRegister(UserProfileRegisterDto userProfileRegisterDto){
        System.out.println("나 여기 프로필 서비스얌");

        User user = accountRepository.findByUserId(userProfileRegisterDto.getUserId());

        if (userProfileRepository.existsByUserAndUserProfileNickname(user, userProfileRegisterDto.getUserProfileNickname())) {
            return DefaultResultDto.builder().success(false).message("이미 사용 중인 프로필 이름입니다.").build();
        }


        UserProfile userProfile = new UserProfile();

        userProfile.setUser(user);
        userProfile.setUserProfileNickname(userProfileRegisterDto.getUserProfileNickname());
        userProfile.setUserPhoneNumber(userProfileRegisterDto.getUserPhoneNumber());
        userProfile.setUserAddress(userProfileRegisterDto.getUserAddress());
        userProfile.setUserMainProfile(0);
        userProfileRepository.save(userProfile);


        return DefaultResultDto.builder().success(true).message("성공").build();
    }
}

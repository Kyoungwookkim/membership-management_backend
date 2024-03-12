package app.membershipmanagement_backend.api.user.service;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.repository.AccountRepository;
import app.membershipmanagement_backend.api.repository.UserProfileRepository;
import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.entity.UserProfile;
import app.membershipmanagement_backend.api.user.dto.UserProfileEditDto;
import app.membershipmanagement_backend.api.user.dto.UserProfileRegisterDto;
import app.membershipmanagement_backend.api.user.dto.UserProfileDeleteDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final AccountRepository accountRepository;
    private final UserProfileRepository userProfileRepository;

    @Transactional
    public DefaultResultDto profileRegister(UserProfileRegisterDto userProfileRegisterDto) {
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

    @Transactional
    public DefaultResultDto profileUpdate(UserProfileEditDto userProfileEditDto) {
        System.out.println("나 여기 프로필 서비스얌");

        User user = accountRepository.findByUserId(userProfileEditDto.getUserId());

        if (userProfileRepository.existsByUserAndUserProfileNickname(user, userProfileEditDto.getUserProfileNickname())) {
            return DefaultResultDto.builder().success(false).message("이미 사용 중인 프로필 이름입니다.").build();
        }


        UserProfile userProfile = new UserProfile();
        userProfile.setUserProfileNum(userProfileEditDto.getUserProfileNum());
        userProfile.setUser(user);
        userProfile.setUserProfileNickname(userProfileEditDto.getUserProfileNickname());
        userProfile.setUserPhoneNumber(userProfileEditDto.getUserPhoneNumber());
        userProfile.setUserAddress(userProfileEditDto.getUserAddress());
        userProfile.setUserMainProfile(0);

        userProfileRepository.save(userProfile);

        return DefaultResultDto.builder().success(true).message("프로필 업데이트 성공").build();
    }

    @Transactional
    public DefaultResultDto deleteProfile(UserProfileDeleteDto userProfiledeleteDto) {

        // userId와 userProfileNickname을 사용하여 UserProfile을 찾습니다.
        User user = accountRepository.findByUserId(userProfiledeleteDto.getUserId());
        String userProfileNickname = userProfiledeleteDto.getUserProfileNickname();

        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserAndUserProfileNickname(user, userProfileNickname).stream().findFirst();

        if (userProfileOptional.isEmpty()) {
            return DefaultResultDto.builder().success(false).message("프로필을 찾을 수 없습니다.").build();
        }

        UserProfile userProfile = userProfileOptional.get();

        // 추가적인 검사: userMainProfile이 1이면 삭제하지 않음
        if (userProfile.getUserMainProfile() == 1) {
            return DefaultResultDto.builder().success(false).message("메인 프로필은 삭제할 수 없습니다.").build();
        }

        // 필요한 경우 삭제 전에 추가적인 검사를 수행합니다.

        userProfileRepository.delete(userProfile);

        return DefaultResultDto.builder().success(true).message("프로필 삭제 성공").build();
    }



}




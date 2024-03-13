package app.membershipmanagement_backend.api.account.service;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.account.dto.UserRegisterDto;
import app.membershipmanagement_backend.api.repository.AccountRepository;
import app.membershipmanagement_backend.api.repository.UserProfileRepository;
import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.entity.UserProfile;
import app.membershipmanagement_backend.api.account.dto.UserDeleteDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final AccountRepository accountRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;



    @Transactional
    public DefaultResultDto register(UserRegisterDto userRegisterDto){

        if (accountRepository.existsByUserId(userRegisterDto.getUserId())) {
            return DefaultResultDto.builder().success(false).message("중복된 아이디가 있습니다.").build();
        }
        User user = new User();
        user.setUserId(userRegisterDto.getUserId());
        user.setUserPassword(passwordEncoder.encode(userRegisterDto.getUserPassword()));
        user.setUserName(userRegisterDto.getUserName());

        accountRepository.save(user);

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setUserProfileNickname(userRegisterDto.getUserProfileNickname());
        userProfile.setUserPhoneNumber(userRegisterDto.getUserPhoneNumber());
        userProfile.setUserAddress(userRegisterDto.getUserAddress());
        userProfile.setUserMainProfile(1);
        userProfileRepository.save(userProfile);


        return DefaultResultDto.builder().success(true).message("성공").build();
    }


    @Transactional
    public DefaultResultDto deleteUser(UserDeleteDto userDeleteDto) {

        User user = accountRepository.findByUserId(userDeleteDto.getUserId());

        if (user == null) {
            return DefaultResultDto.builder().success(false).message("사용자를 찾을 수 없습니다.").build();
        }

        List<UserProfile> userProfiles = userProfileRepository.findByUser(user);

        userProfileRepository.deleteAll(userProfiles);

        accountRepository.delete(user);

        return DefaultResultDto.builder().success(true).message("사용자 및 관련 프로필 삭제 성공").build();
    }

}

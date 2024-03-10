package app.membershipmanagement_backend.api.account.service;

import app.membershipmanagement_backend.api.DefaultResultDto;
import app.membershipmanagement_backend.api.account.dto.UserRegisterDto;
import app.membershipmanagement_backend.api.account.repository.AccountRepository;
import app.membershipmanagement_backend.api.account.repository.UserProfileRepository;
import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.entity.UserProfile;
import app.membershipmanagement_backend.api.global.exception.CustomException;
import app.membershipmanagement_backend.api.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
/*import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AccountRepository accountRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;



    @Transactional
    public DefaultResultDto register(UserRegisterDto userRegisterDto){
        System.out.println("나 서비스 들어온 응애");
        if (accountRepository.existsByUserId(userRegisterDto.getUserId())) {
            throw new CustomException("아이디가 이미 존재합니다.", ErrorCode.SIGN_UP_ID_DUPLICATE);
        }
        User user = new User();
        user.setUserId(userRegisterDto.getUserId());
        user.setUserPassword(passwordEncoder.encode(userRegisterDto.getUserPassword()));
        user.setUserName(userRegisterDto.getUserName());

        accountRepository.save(user);

        User User2 = accountRepository.findByUserId(userRegisterDto.getUserId());

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(User2);
        userProfile.setUserProfileNickname(userRegisterDto.getUserProfileNickname());
        userProfile.setUserPhoneNumber(userRegisterDto.getUserPhoneNumber());
        userProfile.setUserAddress(userRegisterDto.getUserAddress());
        userProfile.setUserMainProfile(1);
        userProfileRepository.save(userProfile);




        return DefaultResultDto.builder().success(true).message("성공").build();
    }

}

package app.membershipmanagement_backend.api.search.service;

import app.membershipmanagement_backend.api.account.repository.AccountRepository;
import app.membershipmanagement_backend.api.account.repository.UserProfileRepository;
import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.entity.UserProfile;
import app.membershipmanagement_backend.api.search.dto.FindUserDto;
import app.membershipmanagement_backend.api.search.dto.FindUserListDto;
import app.membershipmanagement_backend.api.search.dto.FindUserProfileDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindUserService {


    private final AccountRepository accountRepository;

    private final UserProfileRepository userProfileRepository;

    public Page<FindUserDto> getUserList(Pageable pageable,String keyword) {

        List<UserProfile> profiles;
        if (StringUtils.hasText(keyword)) {
            profiles = userProfileRepository.findAllByUserMainProfileAndUserUserNameContaining(1, keyword, pageable);
        } else {
            profiles = userProfileRepository.findAllByUserMainProfile(1, pageable);
        }

        List<FindUserDto> findUserDtoList = new ArrayList<>();

        for (UserProfile userProfile : profiles) {
            FindUserDto findUserDto = new FindUserDto();
            findUserDto.setUserNum(userProfile.getUser().getUserNum());
            findUserDto.setUserId(userProfile.getUser().getUserId());
            findUserDto.setUserName(userProfile.getUser().getUserName());
            findUserDto.setUserNickName(userProfile.getUserProfileNickname());
            findUserDto.setUserPhoneNumber(userProfile.getUserPhoneNumber());
            findUserDto.setUserAddress(userProfile.getUserAddress());

            findUserDtoList.add(findUserDto);
        }

        return new PageImpl<>(findUserDtoList, pageable, findUserDtoList.size());
    }


    public FindUserListDto getUserDetail(Long userNum){
        User user = accountRepository.findByUserNum(userNum);
        FindUserListDto findUserListDto = new FindUserListDto();
        findUserListDto.setUserId(user.getUserId());
        findUserListDto.setUserName(user.getUserName());

        List<FindUserProfileDetailDto> findUserProfileDetailDtos = new ArrayList<>();

        List<UserProfile> userProfiles = userProfileRepository.findByUser(user);

        for (UserProfile userProfile : userProfiles) {
            FindUserProfileDetailDto dto = new FindUserProfileDetailDto();
            dto.setUserProfileNickname(userProfile.getUserProfileNickname());
            dto.setUserPhoneNumber(userProfile.getUserPhoneNumber());
            dto.setUserAddress(userProfile.getUserAddress());
            findUserProfileDetailDtos.add(dto);
        }

       findUserListDto.setFindUserProfileDetailDtoList(findUserProfileDetailDtos);

        return findUserListDto;
    }


}

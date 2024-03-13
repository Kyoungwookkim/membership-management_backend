package app.membershipmanagement_backend.api.search.dto;

import app.membershipmanagement_backend.api.entity.UserProfile;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindUserProfileDetailDto {

    private String userProfileNickname;

    private String userPhoneNumber;

    private String userAddress;


    public FindUserProfileDetailDto(UserProfile userProfile){

        this.userProfileNickname = userProfile.getUserProfileNickname();

        this.userPhoneNumber = userProfile.getUserPhoneNumber();

        this.userAddress = userProfile.getUserAddress();
    }
}

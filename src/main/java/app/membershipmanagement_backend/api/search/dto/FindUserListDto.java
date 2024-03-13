package app.membershipmanagement_backend.api.search.dto;

import app.membershipmanagement_backend.api.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindUserListDto {


    private String userId;


    private String userName;

    private List<FindUserProfileDetailDto> FindUserProfileDetailDtoList;

    public FindUserListDto(User user) {

        this.userId = user.getUserId();
        this.userName = user.getUserName();

    }
}

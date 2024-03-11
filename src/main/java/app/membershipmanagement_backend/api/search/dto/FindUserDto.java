package app.membershipmanagement_backend.api.search.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindUserDto {

    private Long userNum;

    private String userId;

    private String userName;

    private String userNickName;

    private String userPhoneNumber;

    private String userAddress;
}

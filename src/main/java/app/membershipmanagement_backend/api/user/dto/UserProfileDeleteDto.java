package app.membershipmanagement_backend.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDeleteDto {


    String userId;

    @NotNull(message = "프로필이름은 최소 2자 이상 8자 이하로 작성 해주세요.")
    @NotBlank(message = "프로필이름은 최소 2자 이상 8자 이하로 작성 해주세요.")
    @Length(min = 2 , max = 8)
    String userProfileNickname;



}

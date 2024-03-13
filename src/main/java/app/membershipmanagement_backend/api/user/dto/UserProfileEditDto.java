package app.membershipmanagement_backend.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileEditDto {


    String userId;

    Long userProfileNum;

    @NotNull(message = "프로필이름은 최소 2자 이상 8자 이하로 작성 해주세요.")
    @NotBlank(message = "프로필이름은 최소 2자 이상 8자 이하로 작성 해주세요.")
    @Length(min = 2 , max = 8)
    String userProfileNickname;

    @NotNull(message = "전화번호를 작성해 주세요")
    @NotBlank(message = "전화번호를 작성해주세요.")
    @Pattern(regexp = "^[0-9]*$", message = "0~9까지의 숫자만 가능합니다")
    @Length(min = 10, max = 11, message = "전화번호를 올바르게 적어주세요.")
    String userPhoneNumber;

    @NotNull(message = "전화번호를 작성해 주세요")
    @NotBlank(message = "전화번호를 작성해주세요.")
    String userAddress;

}

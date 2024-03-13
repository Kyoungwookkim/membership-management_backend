package app.membershipmanagement_backend.api.account.dto;

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
public class UserRegisterDto {

    @NotNull(message = "아이디는 최소 5자 이상 20자 이하로 작성 해주세요.")
    @NotBlank(message = "아이디는 최소 5자 이상 20자 이하로 작성 해주세요.")
    @Length(max = 20, min = 5)
    private String userId;

    @NotNull(message = "패스워드는 최소 8자 이상, 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다.")
    @NotBlank(message = "패스워드는 최소 8자 이상, 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다.")
    @Length(min = 8)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$" ,message = "패스워드는 최소 8자 이상, 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다.")
    private String userPassword;

    @NotNull(message = "이름은 최소 2자 이상 8자 이하로 작성 해주세요.")
    @NotBlank(message = "이름은 최소 2자 이상 8자 이하로 작성 해주세요.")
    @Length(min = 2 , max = 8)
    private String userName;

    @NotNull(message = "프로필이름은 최소 2자 이상 8자 이하로 작성 해주세요.")
    @NotBlank(message = "프로필이름은 최소 2자 이상 8자 이하로 작성 해주세요.")
    @Length(min = 2 , max = 8)
    private String userProfileNickname;

    @NotNull(message = "전화번호를 작성해 주세요")
    @NotBlank(message = "전화번호를 작성해주세요.")
    @Pattern(regexp = "^[0-9]*$", message = "0~9까지의 숫자만 가능합니다")
    @Length(min = 10, max = 11, message = "전화번호를 올바르게 적어주세요.")
    private String userPhoneNumber;

    @NotNull(message = "전화번호를 작성해 주세요")
    @NotBlank(message = "전화번호를 작성해주세요.")
    private String userAddress;

}

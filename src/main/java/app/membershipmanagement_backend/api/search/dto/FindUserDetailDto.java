package app.membershipmanagement_backend.api.search.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindUserDetailDto {

    private Long userNum;

    private String test;
}

package app.membershipmanagement_backend.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "TB_USER_PROFILE")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_PROFILE_NUM")
    private Long userProfileNum;

    @ManyToOne
    @JoinColumn(name = "USER_NUM")
    private User user;

    @Column(name = "USER_PROFILE_NICKNAME", length = 20, nullable = false)
    private String userProfileNickname;

    @Column(name = "USER_PHONE_NUMBER", length = 20, nullable = false)
    private String userPhoneNumber;

    @Column(name = "USER_ADDRESS", length = 100, nullable = false)
    private String userAddress;

    @Column(name = "USER_MAIN_PROFILE")
    private int userMainProfile;
}

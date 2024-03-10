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
@Table(name = "TB_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NUM")
    private Long userNum;

    @Column(unique = true, name = "USER_ID", length = 20, nullable = false)
    private String userId;

    @Column(name = "USER_PASSWORD", length = 100, nullable = false)
    private String userPassword;

    @Column(unique = true, name = "USER_NAME", length = 20, nullable = false)
    private String userName;
}

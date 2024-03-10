package app.membershipmanagement_backend.api.account.repository;

import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    boolean existsByUserAndUserProfileNickname(User user, String userProfileNickname);
}

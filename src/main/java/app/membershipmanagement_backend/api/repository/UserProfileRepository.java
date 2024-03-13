package app.membershipmanagement_backend.api.repository;

import app.membershipmanagement_backend.api.entity.User;
import app.membershipmanagement_backend.api.entity.UserProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {


    boolean existsByUserAndUserProfileNickname(User user, String userProfileNickname);
    List<UserProfile> findByUserAndUserProfileNickname(User user, String userProfileNickname);

    List<UserProfile> findByUser(User user);


    List<UserProfile> findAllByUserMainProfile(int i,  Pageable pageable);

    List<UserProfile> findAllByUserMainProfileAndUserUserNameContaining(int i, String keyword,  Pageable pageable);
}

package app.membershipmanagement_backend.api.account.repository;

import app.membershipmanagement_backend.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);

    User findByUserId(String userId);

}

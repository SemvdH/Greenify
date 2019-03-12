package greenify.server.data.repository;

import greenify.server.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends UserRepository, JpaRepository<User,Long> {

}
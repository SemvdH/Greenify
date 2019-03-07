package Server.data.gogreen.repository;

import Server.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends Server.data.gogreen.repository.UserRepository, JpaRepository<User,Long> {

}
package gogreen.server.data.repository;

import gogreen.server.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends UserRepository, JpaRepository<User,Long> {

}
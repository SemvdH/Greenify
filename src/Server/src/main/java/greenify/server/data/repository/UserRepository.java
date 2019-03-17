package greenify.server.data.repository;

import org.springframework.data.repository.CrudRepository;
import greenify.server.data.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
    <T extends User> T save(T user);
}


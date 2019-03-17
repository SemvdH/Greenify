package greenify.server.data.repository;

import greenify.server.data.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);

    <T extends User> T save(T user);
}


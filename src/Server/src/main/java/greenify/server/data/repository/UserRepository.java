package greenify.server.data.repository;

import greenify.server.data.model.User;
import org.springframework.data.repository.CrudRepository;

//userRepository that saves all the user and talks to the database
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);

    <T extends User> T save(T user);
}


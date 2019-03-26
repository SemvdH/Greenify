package greenify.server.data.repository;

import greenify.server.data.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository that saves all the users and talks to the database.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);

    /**
     * This method saves a user in the database.
     * @param user the user you want saved
     * @param <T> always a user
     * @return the user
     */
    <T extends User> T save(T user);
}

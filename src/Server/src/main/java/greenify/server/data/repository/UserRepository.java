package greenify.server.data.repository;

import greenify.server.data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(@Param("name") String name);
    //    User findByName(String name);
    <T extends User> T save(T user);
}


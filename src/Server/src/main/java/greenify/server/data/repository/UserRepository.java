package greenify.server.data.repository;

import greenify.server.data.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    //    User findByName(@Param("name") String name);
    User findByName(String name);
    User findById(Long id);
    <T extends User> T save(T user);
}


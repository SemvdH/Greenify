package greenify.server.data.repository;

import greenify.server.data.model.User;

public interface UserRepository {
    User findByName(String name);
    <T extends User> T save(T user);
}

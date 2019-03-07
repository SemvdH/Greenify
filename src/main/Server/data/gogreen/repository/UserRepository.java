package Server.data.gogreen.repository;

import Server.data.model.User;

public interface UserRepository {
    User findByName(String name);
    <T extends User> T save(T user);
}

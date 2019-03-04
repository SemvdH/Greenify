package gogreen.server.data.repository;

import gogreen.server.data.model.User;

public interface UserRepository {
    User findByName(String name);
    <T extends User> T save(T user);
}

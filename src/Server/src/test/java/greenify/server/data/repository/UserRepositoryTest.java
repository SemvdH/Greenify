package greenify.server.data.repository;

import static org.junit.Assert.assertEquals;
import greenify.server.data.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void findByUsernameTest() {
        repository.save(new User(296L, "cugurlu", "password"));
        User user = this.repository.findByName("cugurlu");
        assertEquals(user.getName(), "cugurlu");
    }

}

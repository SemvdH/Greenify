//package greenify.server.data.repository;
//
//import greenify.server.data.model.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static junit.framework.TestCase.assertTrue;
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class UserRepositoryTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private UserRepository repository;
//
//    @Test
//    public void findByUsernameShouldReturnUser() throws Exception {
//        this.entityManager.persist(new User(296L, "cugurlu", "password", 6));
//        User user = this.repository.findByName("cugurlu");
//        assertEquals(user.getName(), "cugurlu");
//    }
//
//    @Test
//    public void findByUsernameWhenNoUserShouldReturnNull() throws Exception {
//        this.entityManager.persist(new User(296L, "cugurlu", "password", 6));
//        User user = this.repository.findByName("mouse");
//        assertTrue(user == null);
//    }
//}

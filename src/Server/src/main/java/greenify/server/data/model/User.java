package greenify.server.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
public class User {

    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String name;
    private String password;

    /**
     * makes a user object.
     * @param id the id of the user.
     * @param name the supplied username
     * @param password the supplied password
     */
    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /**
     * gets the id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * gets the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * gets the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {

        return "User(id=" + this.id + ", name=" + this.name + ", password=" + this.password + ")";
    }
}
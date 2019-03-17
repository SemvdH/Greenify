package greenify.server.data.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@EnableAutoConfiguration
@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    private int veganMeal;

    public User() {}

    /**
     * makes a user object.
     * @param id the id of the user.
     * @param name the supplied username
     * @param password the supplied password
     * @param veganMeal the supplied number of vegan meal
     */
    public User(Long id, String name, String password, int veganMeal) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.veganMeal = veganMeal;
    }

    /**
     * gets the id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    /**
     * gets the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    /**
     * gets the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    /**
     * gets the number of vegan meal.
     * @return the veganMeal
     */
    public int getVeganMeal() {
        return veganMeal;
    }

    public void setVeganMeal(int veganMeal) { this.veganMeal = veganMeal; }

    /**
     * checks if two users are equal.
     * @param other the object to compare the user with
     * @return a boolean value of true if the user is equal to the object
     */
    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        if (other instanceof User) {
            User that = (User) other;
            return this.getName().equals(that.getName())
                    && this.getId().equals(that.getId())
                    && this.getPassword().equals(that.getPassword())
                    && this.getVeganMeal() == that.getVeganMeal();
        }
        return false;
    }

    /**
     * creates a string of the user object.
     * in the form of: User(id=, name=, password=, veganMeal=)
     * @return a string of the user object
     */
    @Override
    public String toString() {
        return "User(id="
                + this.id
                + ", name="
                + this.name
                + ", password="
                + this.password
                + ", veganMeal="
                + this.veganMeal + ")";
    }

    /**
     * hashes the User object.
     * @return a hashcode for the user object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, veganMeal);
    }
}

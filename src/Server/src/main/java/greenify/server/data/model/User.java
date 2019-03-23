package greenify.server.data.model;

import lombok.Data;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * gets the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets the number of vegan meal.
     * @return the veganMeal
     */
    public int getVeganMeal() {
        return veganMeal;
    }

    public void setVeganMeal(int veganMeal) {
        this.veganMeal = veganMeal;
    }


    /**
     * Returns a human readable object. It's in JSON.
     * @return the JSON form of the object.
     */
    @Override
    public String toString() {
        return "User(id=" + this.id + ", name=" + this.name + ", password="
                + this.password + ", veganMeal=" + this.veganMeal + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof User) {
            User that = (User)other;
            if (that.id == this.id && that.name.equals(this.name)
                    && that.password.equals(this.password) && that.veganMeal == this.veganMeal) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, veganMeal);
    }
}

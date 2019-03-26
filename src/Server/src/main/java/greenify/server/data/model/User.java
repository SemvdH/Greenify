package greenify.server.data.model;

import greenify.server.InputValidator;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.ElementCollection;
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

    @NotNull
    private Float footPrint = 0.0f;

    @ElementCollection
    private Map<String, String> footPrintInputs = new HashMap<>();

    User() { }

    /**
     * This method makes a user object.
     * @param id the id of the user.
     * @param name the supplied username
     * @param password the supplied password
     */
    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.setFootPrintInputs(InputValidator.getDefaultValues());
    }

    /**
     * This method returns the ID of the user.
     * @return the id of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * This method sets the ID of the user.
     * @param id the id of the user
     */
    void setId(Long id) {
        this.id = id;
    }

    /**
     * This method returns the name of the user.
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of the user.
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the password of the user.
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets the password of the user.
     * @param password the password of the user
     */
    void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method returns the footPrint of user.
     * @return the footprint of the user
     */
    public Float getFootPrint() {
        return footPrint;
    }

    /**
     * This method sets the footprint of a user.
     * @param footPrint footprint of a user
     */
    public void setFootPrint(Float footPrint) {
        this.footPrint = footPrint;
    }

    /**
     * This method returns the footprint inputs of the user.
     * @return footprint inputs of the user
     */
    public Map<String, String> getFootPrintInputs() {
        return footPrintInputs;
    }

    /**
     * This method sets the footprint inputs of the user.
     * @param footPrintInputs footprint inputs of the user
     */
    public void setFootPrintInputs(Map<String, String> footPrintInputs) {
        this.footPrintInputs = footPrintInputs;
    }

    /**
     * This method returns a human readable (JSON) object.
     * @return the JSON form of the object.
     */
    @Override
    public String toString() {
        return "User(id=" + this.id + ", name=" + this.name + ", password="
                + this.password + ")";
    }

    /**
     * This method checks whether two users are equal or not.
     * @param other an other user
     * @return users are (not) equal
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof User) {
            User that = (User) other;
            return that.id.equals(this.id) && that.name.equals(this.name)
                    && that.password.equals(this.password);
        }
        return false;
    }

    /**
     * This method returns the hashcode of a user.
     * @return hashcode of a user
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }
}

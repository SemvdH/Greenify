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
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private Float footPrint = 0.0f;

    @ElementCollection
    private Map<String,String> footPrintInputs = new HashMap<>();

    @ManyToMany
    @JoinColumn
    private Collection<User> friends;

    public User() {}

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
        this.setFootPrintInputs(InputValidator.getDefaultValues());
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
     * gets the footPrint of user.
     * @return the footPrint
     */
    public Float getFootPrint() {
        return footPrint;
    }

    public Map<String, String> getFootPrintInputs() {
        return footPrintInputs;
    }

    public void setFootPrintInputs(Map<String, String> footPrintInputs) {
        this.footPrintInputs = footPrintInputs;
    }

    public ArrayList<User> getFriends(){
        return (ArrayList<User>)this.friends;
    }

    public void setFootPrint(Float footPrint) {
        this.footPrint = footPrint;
    }


    /**
     * Returns a human readable object. It's in JSON.
     * @return the JSON form of the object.
     */
    @Override
    public String toString() {
        return "User(id=" + this.id + ", name=" + this.name + ", password="
                + this.password + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof User) {
            User that = (User)other;
            if (that.id == this.id && that.name.equals(this.name)
                    && that.password.equals(this.password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }
}

package greenify.server.data.model;

import greenify.common.ApplicationException;
import greenify.server.Application;
import lombok.Data;

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

    private int veganMeal;

//    @ElementCollection
//    @CollectionTable(name = "friends", joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "friendId")
    @ManyToMany
    @JoinColumn
    private Collection<User> friends;

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
        this.friends = new ArrayList<User>();
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

    public ArrayList<User> getFriends(){
        return (ArrayList<User>)this.friends;
    }

    public void addFriend(User user){
        if(!user.equals(this)) {
            friends.add(user);
        }
        else {
            throw new ApplicationException("Cannnot add yourself as a friend");
        }
    }

    public String friendsToString(){
        String result = "";
        for(User u : friends){
            result += u.getName() + ", ";
        }
        if(result.endsWith(", ")){
            result = result.substring(0, result.lastIndexOf(","));
        }
        return result;
    }

    /**
     * Returns a human readable object. It's in JSON.
     * @return the JSON form of the object.
     */
    @Override
    public String toString() {
        String result = "User(id=" + this.id + ", name=" + this.name + ", password="
                + this.password + ", veganMeal=" + this.veganMeal + ", friends=[";
        result += friendsToString() + "])";

//                result += ")";
                return result;
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

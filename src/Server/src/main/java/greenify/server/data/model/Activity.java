package greenify.server.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Scanner;

@Entity
@Data
//@AllArgsConstructor
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String description;
    int score;

    /**
     * makes an activity object.
     * @param id the id of the activity.
     * @param name the name of the feature.
     * @param description the description of the feature.
     * @param score the amount of points a user gets for doing this activity.
     */
    public void User(Long id, String name, String description, int score) {
        this.id = id;
        this.name = name;
        this.description = setDes(description);
        this.score = score;
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
    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * sets the name.
     * @param name the you want to assign to this.name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the description.
     * @param description the description to be set.
     */
    public String setDes(String description){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the description");
        description = scan.nextLine();
        scan.close();
        return description;
    }

    /**
     * sets the score you get for an activity.
     * @param score the you want to assign to the activity.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns a human readable object. It's in JSON.
     * @return the JSON form of the object.
     */
    @Override
    public String toString() {
        return "Activity(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", score=" + this.score + ")";
    }
}
package greenify.server.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.accessibility.AccessibleValue;
import javax.persistence.*;
import java.util.Objects;
import java.util.Scanner;

@Entity
@Data
@Table(name = "activities")
//@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
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
    public Activity(long id, String name, String description, int score) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.score = score;
    }

    /**
     * gets the id.
     * @return the id
     */
    public long getId() {
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
     * gets the description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets the score.
     * @return the score
     */
    public int getScore() {
        return score;
    }


    /**
     * sets the id.
     * @param id the you want to assign to this.id.
     */
    public void setId(long id) {
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
    public void setDescription(String description){
        this.description = description;
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

    @Override
    public boolean equals(Object other) {
        if(other instanceof Activity){
            Activity that = (Activity)other;
            if(that.id != this.id)
                return false;
            if(!that.name.equals(this.name))
                return false;
            if(!that.description.equals(this.description))
                return false;
            if(that.score != this.score)
                return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, score);
    }
}
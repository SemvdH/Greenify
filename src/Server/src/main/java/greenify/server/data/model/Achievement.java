package greenify.server.data.model;

import java.util.Objects;

public class Achievement {
    private String name;
    private String description;
    private boolean achieved;

    public Achievement() {}

    /**
     * Constructor for an achievement.
     * @param name name of the achievement
     * @param description description of the achievement
     * @param achieved whether the achievement is achieved or not
     */
    public Achievement(String name, String description, boolean achieved) {
        this.name = name;
        this.description = description;
        this.achieved = achieved;
    }

    /**
     * This method sets the name of an achievement.
     * @return name of the achievement
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of an achievement.
     * @param name name of the achievement
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method gets the description of an achievement.
     * @return the description of the achievement
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method sets the name of an achievement.
     * @param description the description of an achievement
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method gets the state of an achievement.
     * @return achievement is (not) achieved
     */
    public boolean isAchieved() {
        return achieved;
    }

    /**
     * This method sets the state of an achievement.
     * @param achieved achievement is (not) achieved
     */
    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    /**
     * This method gets a human readable (JSON) object.
     * @return the JSON form of the object
     */
    @Override
    public String toString() {
        return "Achievement(name=" + name + ", description=" + description
                + ", achieved=" + achieved + ")";
    }

    /**
     * This method checks whether two users are equal or not.
     * @param other another achievement
     * @return achievements are (not) equal
     */
    @Override
    public boolean equals(Object other) {
        if(other == null){
            return false;
        }
        if (other instanceof Achievement) {
            Achievement that = (Achievement) other;
            return achieved == that.achieved
                    && name.equals(that.name)
                    && Objects.equals(description, that.description);
        }
        return false;
    }

    /**
     * This method gets the hashcode of an achievement.
     * @return hashcode of an achievement
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description, achieved);
    }
}

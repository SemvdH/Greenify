package greenify.common;

public class ActivityDto {
    private Long id;
    private String name;
    private String description;
    private int score;

    public ActivityDto() {
    }

    /**
     * Constructor for ActivityDto.
     * @param id id of the activity
     * @param name name of the activity
     * @param description description of the activity
     * @param score score of the activity
     */
    public ActivityDto(Long id, String name, String description, int score) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.score =  score;
    }

    //all the getters and setters of the class

    /**
     * gets the name of the activity.
     * @return the name of the activity
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of the activity.
     * @param name the name to be set of the activity.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the id of the activity.
     * @return the id of the activity.
     */
    public Long getId() {
        return id;
    }

    /**
     * sets the id of the activity.
     * @param id the id to be set of the activity.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * gets the description of the activity.
     * @return the description of the activity.
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of the activity.
     * @param description the description to be set of the activity.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets the score of the activity.
     * @return the score of the activity.
     */
    public int getScore() {
        return score;
    }

    /**
     * sets the score of the activity.
     * @param score the score to be set of the activity.
     */
    public void setScore(int score) {
        this.score = score;
    }
}

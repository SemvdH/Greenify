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
     * @param id of the activity
     * @param name of the activity
     * @param description of the activity
     * @param score of the activity
     */
    public ActivityDto(Long id, String name, String description, int score) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.score =  score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

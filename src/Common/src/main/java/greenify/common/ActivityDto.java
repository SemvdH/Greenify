package greenify.common;

public class ActivityDto {
    private Long id;
    private String name;
    private String description;
    private int score;

    public ActivityDto() {
    }

    public ActivityDto(Long id, String name, String description, int score) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.score= score;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getScore() {
        return score;
    }
}

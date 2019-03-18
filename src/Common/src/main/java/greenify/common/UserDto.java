package greenify.common;

// DTO stands for Data Transfer Object.
// is an object that carries data between processes.
// The motivation for its use is that communication between processes is usually done
// resorting to remote interfaces (e.g., web services), where each call is an expensive operation.
public class UserDto {
    private Long id;
    private String name;
    private int veganMeal;

    public UserDto() {
    }

    /**
     * The constructor method of UserDto.
     * @param id of the user
     * @param name of the user
     * @param veganMeal the number of vegetarian meals eaten
     */
    public UserDto(Long id, String name, int veganMeal) {
        this.id = id;
        this.name = name;
        this.veganMeal = veganMeal;
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

    public int getVeganMeal() {
        return veganMeal;
    }

    public void setVeganMeal(int veganMeal) {
        this.veganMeal = veganMeal;
    }
}
package greenify.common;

// DTO stands for Data Transfer Object.
// is an object that carries data between processes.
// The motivation for its use is that communication between processes is usually done
// resorting to remote interfaces (e.g., web services), where each call is an expensive operation.
public class UserDTO {

    private Long id;
    private String name;

    public UserDTO() {
    }

    public UserDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
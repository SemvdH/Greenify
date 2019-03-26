/*
  DTO stands for Data Transfer Object.
  is an object that carries data between processes.
  The motivation for its use is that communication between processes
  is usually done
  resorting to remote interfaces (e.g., web services),
  where each call is an expensive operation.
 */
package greenify.common;

public class UserDto {
    private Long id;
    private String name;

    public UserDto() {
    }

    /**
     * The constructor method of UserDto.
     * @param id of the user
     * @param name of the user
     */
    public UserDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Method that returns the name of a user.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method that sets the name of a user.
     * @param name name of a user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that returns the ID of a user.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Method that sets the ID of a user.
     * @param id id of a user
     */
    public void setId(Long id) {
        this.id = id;
    }
}

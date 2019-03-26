package greenify.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * This method starts the server.
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//application to boot with spring
@SpringBootApplication
public class Application {

    //run the application
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
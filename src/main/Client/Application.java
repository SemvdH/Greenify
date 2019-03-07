package Client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class Application extends javafx.application.Application {
    private ConfigurableApplicationContext springContext;
    private Parent rootNode;
    private FXMLLoader fxmlLoader;
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        launch(args);
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

    @Override
    public void init() throws Exception {
        springContext = run(Application.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        fxmlLoader.setLocation(this.getClass().getClassLoader().getResource("main/Client/fxml/sample.fxml"));
//        fxmlLoader.setLocation(Client.Application.class.getResource("resources/fxml/sample.fxml"));
        Parent rootNode = fxmlLoader.load();
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
//        Image image = new Image("pinkleaf.png");

//        rootNode = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/sample.fxml"));

        primaryStage.setTitle("GoGreen");
        Scene scene = new Scene(rootNode);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    @Override
//    public void stop() {
//        springContext.stop();
//    }

//    @Bean
//    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//        return args -> {
//            User user = restTemplate.getForObject(
//                    "http://localhost:8080/user", User.class);
//            log.info(user.toString());
//
//        };
//    }
}
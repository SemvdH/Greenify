package greenify.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application extends javafx.application.Application {
    private static ConfigurableApplicationContext springContext;
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        launch(args);
    }

    public static Parent load(java.net.URL url) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(springContext::getBean);
        loader.setLocation(url);
        try {
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(Application.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent rootNode = load(this.getClass().getClassLoader().getResource("fxml/sample.fxml"));
        primaryStage.setTitle("Greenify");
        Scene scene = new Scene(rootNode);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }
}
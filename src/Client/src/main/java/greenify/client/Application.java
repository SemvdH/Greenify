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

import java.io.IOException;

@SpringBootApplication
public class Application extends javafx.application.Application {
    private static ConfigurableApplicationContext springContext;
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method takes an url and return a parent.
     * @param url which is being loaded.
     * @return parent object.
     */
    public static Parent load(java.net.URL url) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(springContext::getBean);
        loader.setLocation(url);
        return loader.load();
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
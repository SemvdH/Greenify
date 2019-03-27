package greenify.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;


@SpringBootApplication
public class Application extends javafx.application.Application {
    private static ConfigurableApplicationContext springContext;

    /**
     * This (main) method starts launch.
     * @param args the arguments
     */
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

    /**
     * This method initializes the application.
     */
    @Override
    public void init() {
        springContext = SpringApplication.run(Application.class);
    }

    /**
     * This method opens the login window.
     * @param primaryStage the login window
     * @throws Exception in case fxml file is not found
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent rootNode = load(this.getClass().getClassLoader()
                .getResource("fxml/LoginWindow.fxml"));
        primaryStage.setTitle("Greenify");
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("stylesheets/LoginWindowStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method stops the application.
     */
    @Override
    public void stop() {
        springContext.stop();
    }
}

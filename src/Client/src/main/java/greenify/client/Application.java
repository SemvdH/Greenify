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

//springbootApplication is so Spring knows that this is a Spring application
@SpringBootApplication
public class Application extends javafx.application.Application {
    //configurable application is for spring so it knows that it can use it
    private static ConfigurableApplicationContext springContext;
    //logger to log all the things that happen to the console
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    //launch is to launch the GUI things
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method takes an url and return a parent.
     * @param url which is being loaded.
     * @return parent object.
     * @throws IOException if it can't find an FXML file
     */
    public static Parent load(java.net.URL url) throws IOException {
        //loader to load the FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(springContext::getBean);
        loader.setLocation(url);
        return loader.load();
    }

    @Override
    public void init() throws Exception {
        //run the application
        springContext = SpringApplication.run(Application.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //load the fxml file
        Parent rootNode = load(this.getClass().getClassLoader().getResource("fxml/sample.fxml"));
        //set the title for the window
        primaryStage.setTitle("Greenify");
        //set the scene
        Scene scene = new Scene(rootNode);
        //add the stylesheet
        scene.getStylesheets()
                .add(getClass().getClassLoader().getResource("stylesheets/LoginWindowStyle.css")
                        .toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }
}
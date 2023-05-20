package de.todoapp.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static final double WIDTH = 860.;
    public static final double HEIGHT = 560.;
    public static final String TITLE = "To-Do Desktop";
    private static final Logger logger = LogManager.getLogger(Main.class);

    /**
     * Default constructor for the Main class.
     */
    public Main() {}

    /**
     * The start() method is called by the JavaFX framework when the application is launched.
     * It loads a fxml file and creates a new stage to display it.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/fxml/TodayTasks.fxml")));
        Parent root = loader.load();

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle(TITLE);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is the entry point of the Java application.
     * It launches the JavaFX application by calling the launch() method of the Application class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        logger.info("The app has been started.");
        Main.launch(args);
    }
}

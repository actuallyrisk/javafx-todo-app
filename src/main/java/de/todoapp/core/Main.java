package de.todoapp.core;

import de.todoapp.controller.AddTaskController;
import de.todoapp.controller.BaseController;
import de.todoapp.controller.TodayTasksController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import javafx.scene.control.TableView;

public class Main extends Application {
    public static final double WIDTH = 860.;

    public static final double HEIGHT = 560.;

    public static final String TITLE = "To-Do Desktop";

    public static final String FXML_FOLDER_PATH = "/views/";

    private static final Logger LOGGER = LogManager.getLogger(Main.class);




    /**
     * Default constructor for the Main class.
     */
    public Main() {
    }

    /**
     * The main() method is the entry point of the Java application.
     * It launches the JavaFX application by calling the launch() method of the Application class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        LOGGER.info("The app has been started.");
        Main.launch(args);
    }

    /**
     * The start() method is called by the JavaFX framework when the application is launched.
     * It loads a views file and creates a new stage to display it.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {

        // Loads the database and the FXML files concurrently
        DatabaseLoader databaseLoader = new DatabaseLoader();
        Thread databaseThread = new Thread(databaseLoader);
        databaseThread.start();

        FXMLLoader fxmlLoader = new FXMLLoader(WIDTH, HEIGHT, FXML_FOLDER_PATH);
        Thread fxmlThread = new Thread(fxmlLoader);
        fxmlThread.start();

        try {
            databaseThread.join();
            fxmlThread.join();
        } catch (InterruptedException ex) {
            LOGGER.error(ex.getMessage());
        }

        // Settings for the stage
        stage.setTitle(TITLE);
        stage.setResizable(false);


        // Setting the stage and switching to a scene
        BaseController.getInstance().setStage(stage);
        BaseController.getInstance().switchScene("TodayTasks");

    }


}

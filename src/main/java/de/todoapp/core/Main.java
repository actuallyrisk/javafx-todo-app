package de.todoapp.core;

import de.todoapp.config.AppConfig;
import de.todoapp.controller.BaseController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main class that serves as the entry point of the JavaFX application.
 *
 * @author Anton Horn, Tomislav Zecevic, Luis Kronenbitter, Tobias Metzger
 * @version 1.0
 */
public class Main extends Application {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    private final BaseController baseController = BaseController.getInstance();

    private final FXMLLoader fxmlLoader = FXMLLoader.getInstance();

    private final AppConfig appConfig = AppConfig.getInstance();

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

        //We load the database and the fxml files at parallel to save some time at the startup.

        // Loads the database
        DatabaseLoader databaseLoader = new DatabaseLoader();
        Thread databaseThread = new Thread(databaseLoader);
        databaseThread.start();

        // Loads the FXML files
        fxmlLoader.load();

        try {
            databaseThread.join();
        } catch (InterruptedException ex) {
            LOGGER.error(ex.getMessage());
        }

        // Settings for the stage
        stage.setTitle(appConfig.getTitle());
        stage.setResizable(false);

        // Setting the stage and switching to a scene
        baseController.setStage(stage);
        baseController.switchScene("TodayTasks");
    }
}

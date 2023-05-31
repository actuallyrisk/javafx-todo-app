package de.todoapp.core;

import de.todoapp.controller.BaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main extends Application {
    public static final double WIDTH = 860.;

    public static final double HEIGHT = 560.;

    public static final String TITLE = "To-Do Desktop";

    public static final String FXML_FOLDER_PATH = "/fxml/";

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
     * It loads a fxml file and creates a new stage to display it.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        // Load all existing fxml files
        loadFxmlFiles(FXML_FOLDER_PATH);

        // Call the createDatabase method from DBHandler
        DBHandler.createDatabase();

        // Settings for the stage
        stage.setTitle(TITLE);
        stage.setResizable(false);

        // Setting the stage and switching to a scene
        BaseController.getInstance().setStage(stage);
        BaseController.getInstance().switchScene("TodayTasks");
    }

    /**
     * Loads the FXML files and creates scenes for each file.
     *
     * @param fxmlFolderPath the path to the folder containing the FXML files
     */
    private static void loadFxmlFiles(String fxmlFolderPath) {
        try {
            // Get a list of FXML files in the specified folder
            String[] fxmlFiles = getResourceListing(fxmlFolderPath);

            // Iterate over each FXML file
            for (String fxmlFile : fxmlFiles) {
                // Load the FXML document using FXMLLoader and create a Parent component
                Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlFolderPath + fxmlFile)));

                // Set the scene with the root component and the specified width and height
                BaseController.getInstance().putScene(getKeyFromFileName(fxmlFile), new Scene(root, WIDTH, HEIGHT));

                // Logs a debug message indicating that all fxml files have been loaded.
                LOGGER.debug("All fxml files have been loaded.");
            }
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
    }

    /**
     * Retrieves a list of filenames in the specified resource path.
     *
     * @param path the path to the resource folder or directory
     * @return an array of filenames in the resource folder or directory
     * @throws IOException if an I/O error occurs while accessing the resource
     */
    private static String[] getResourceListing(String path) throws IOException {
        // Create a File object for the specified resource path
        File folder = new File(Objects.requireNonNull(Main.class.getResource(path)).getFile());

        // Return the list of filenames in the resource folder or directory
        return folder.list();
    }

    /**
     * Retrieves a key from a given file name by removing the file extension.
     *
     * @param fileName the name of the file
     * @return the key extracted from the file name
     */
    private static String getKeyFromFileName(String fileName) {
        // Create a Path object based on the given file name
        Path filePath = Paths.get(fileName);

        // Get the file name as a string
        String key = filePath.getFileName().toString();

        // Find the last index of the dot (file extension separator)
        int dotIndex = key.lastIndexOf(".");

        // If the dot is found and it's not the first character, remove the file extension
        if (dotIndex > 0) {
            key = key.substring(0, dotIndex);
        }

        // Return the extracted key
        return key;
    }
}

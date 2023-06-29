package de.todoapp.core;

import de.todoapp.config.AppConfig;
import de.todoapp.controller.BaseController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

import static de.todoapp.utils.FileUtils.*;

/**
 * This is the FXMLLoader class that is responsible for
 * loading FXML files and creating scenes.
 *
 * @author Anton Horn
 * @version 1.0
 */
public class FXMLLoader {
    private static final Logger LOGGER = LogManager.getLogger(FXMLLoader.class);

    private static FXMLLoader instance;

    private static final AppConfig appConfig = AppConfig.getInstance();

    /**
     * Default constructor for the FXMLLoader class.
     */
    private FXMLLoader() {
    }

    /**
     * Retrieves the instance of the FXMLLoader using the singleton pattern.
     * If the instance does not exist, a new instance is created.
     *
     * @return the instance of the FXMLLoader
     */
    public static synchronized FXMLLoader getInstance() {
        if (instance == null) {
            instance = new FXMLLoader();
        }
        return instance;
    }

    /**
     * Loads the FXML files and creates scenes for each file.
     *
     * @return an array of loaded FXML file names
     */
    public String[] load() {
        try {
            // Get a list of FXML files in the specified folder
            String[] fxmlFiles = getResourceListing(appConfig.getFxmlFolderPath());

            // Iterate over each FXML file
            for (String fxmlFile : fxmlFiles) {
                // Load the FXML document using FXMLLoader and create a Parent component
                Parent root = javafx.fxml.FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(appConfig.getFxmlFolderPath() + fxmlFile)));

                // With the "AddTask.fxml" file will be a deviating scene created, with a different size compared to the other scenes
                if (fxmlFile.equals("AddTask.fxml")) {
                    BaseController.getInstance().putScene(getKeyFromFileName(fxmlFile), new Scene(root, 302, 289));
                } else {
                    // Set the scene with the root component and the specified width and height
                    BaseController.getInstance().putScene(getKeyFromFileName(fxmlFile), new Scene(root, appConfig.getWidth(), appConfig.getHeight()));
                }
            }

            // Logs a debug message indicating that all views files have been loaded.
            LOGGER.info("All fxml files have been loaded!");

            return fxmlFiles;
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        return new String[]{};
    }
}

package de.todoapp.core;

import de.todoapp.config.AppConfig;
import de.todoapp.controller.BaseController;
import de.todoapp.utils.MapUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    private final BaseController baseController = BaseController.getInstance();

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
                    baseController.putScene(getKeyFromFileName(fxmlFile), new Scene(root, 300, 380));
                } else {
                    // Set the scene with the root component and the specified width and height
                    baseController.putScene(getKeyFromFileName(fxmlFile), new Scene(root, appConfig.getWidth(), appConfig.getHeight()));
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

    /**
     * Reloads all scenes stored in the SCENE_STORAGE HashMap.
     *
     * @return true if all scenes were successfully reloaded, false if an error occurred
     */
    public boolean reload() {
        // Access the SCENE_STORAGE HashMap from the BaseController
        HashMap<String, Scene> SCENE_STORAGE = baseController.getSceneStorage();

        // Create a temporary HashMap to store the newly loaded scenes
        HashMap<String, Scene> updatedScenes = new HashMap<>();

        // Iterate over each entry (key-scene pair) in the SCENE_STORAGE HashMap
        for (Map.Entry<String, Scene> entry : SCENE_STORAGE.entrySet()) {
            String key = entry.getKey();
            Scene scene = entry.getValue();

            try {
                // Reload the FXML document
                Parent root = javafx.fxml.FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(appConfig.getFxmlFolderPath() + key + ".fxml")));

                // Update the temporary HashMap with the newly loaded Scene
                updatedScenes.put(key, new Scene(root, scene.getWidth(), scene.getHeight()));
            } catch (IOException e) {
                LOGGER.error("Error while reloading scene: " + key);
                LOGGER.error(e.toString());

                // Return false if an error occurred while reloading a scene
                return false;
            }
        }

        // Pass the updated scenes to the BaseController to update SCENE_STORAGE
        baseController.updateSceneStorage(updatedScenes);

        // Logs a debug message that all scenes have been reloaded
        LOGGER.info("All scenes have been reloaded.");

        // Return true if all scenes were successfully reloaded
        return true;
    }

    /**
     * Reloads the specified scene in the SCENE_STORAGE HashMap.
     *
     * @param scene the scene to be reloaded
     * @return true if the scene was successfully reloaded, false otherwise
     */
    public boolean reload(Scene scene) {
        // Get the key of the scene from the SCENE_STORAGE HashMap
        String key = MapUtils.getKeyByValue(baseController.getSceneStorage(), scene);

        // Check if the key exists in the HashMap
        if (key != null) {
            try {
                // Reload the FXML document
                Parent root = javafx.fxml.FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(appConfig.getFxmlFolderPath() + key + ".fxml")));

                // Remove the old scene from the SCENE_STORAGE HashMap
                baseController.removeScene(key);

                // Put the reloaded scene in the SCENE_STORAGE HashMap
                baseController.putScene(key, new Scene(root, scene.getWidth(), scene.getHeight()));

                // Logs an info message that the scene has been reloaded
                LOGGER.info("Scene '{}' has been reloaded.", key);

                return true;
            } catch (IOException e) {
                LOGGER.error("Error while reloading scene: " + key);
                LOGGER.error(e.toString());

                // Return false if an error occurred while reloading the scene
                return false;
            }
        }

        // Return false if the corresponding scene was not found
        return false;
    }
}

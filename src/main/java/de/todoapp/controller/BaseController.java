package de.todoapp.controller;

import de.todoapp.config.AppConfig;
import de.todoapp.core.*;
import de.todoapp.utils.MapUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;
import java.util.Objects;

/**
 * This is the base controller class that provides common functionality for all
 * controllers in the application.
 *
 * @author Luis Kronenbitter, Tobias Metzger
 * @version 1.2
 */
public class BaseController {
    private static BaseController instance;

    protected static AppConfig appConfig = AppConfig.getInstance();

    protected static FXMLLoader fxmlLoader = FXMLLoader.getInstance();

    private Stage stage;

    private static final Logger LOGGER = LogManager.getLogger(BaseController.class);

    private static final HashMap<String, Scene> SCENE_STORAGE = new HashMap<>();

    @FXML
    protected Text themeSwitcherText;

    @FXML
    protected Button themeSwitcherBtn;

    @FXML
    protected Pane paneHeader;

    protected TaskService taskService = new TaskService();

    /**
     * Protected constructor for the BaseController class.
     */
    protected BaseController() {
    }

    /**
     * Retrieves the instance of the BaseController using the singleton pattern.
     * If the instance does not exist, a new instance is created.
     *
     * @return the instance of the BaseController
     */
    public static synchronized BaseController getInstance() {
        if (instance == null) {
            instance = new BaseController();
        }
        return instance;
    }

    /**
     * Initializes the controller. This method is called after the FXML file has been loaded.
     * The method has to be implemented by subclasses to provide the initialization code.
     */
    public void initialize() {
        Scene scene = themeSwitcherBtn.getScene();

        if (scene == null) {
            // Button is not yet added to the scene, wait until the scene is available
            themeSwitcherBtn.sceneProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    addStylesheet(newValue);
                }
            });
        } else {
            addStylesheet(scene);
        }

    }

    /**
     * Adds the stylesheet to the given scene based on the current theme mode.
     *
     * @param scene The scene to which the stylesheet should be added.
     */
    private void addStylesheet(Scene scene) {
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());

        if (appConfig.isDarkMode()) {
            scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/light.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/dark.css")).toExternalForm());
            themeSwitcherText.setText("Too dark? Switch to");
            themeSwitcherBtn.setText("light mode");

            // Saving the mode in the app config
            appConfig.setDarkMode(true);
        } else {
            scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/dark.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/light.css")).toExternalForm());
            themeSwitcherText.setText("Too light? Switch to");
            themeSwitcherBtn.setText("dark mode");

            // Saving the mode in the app config
            appConfig.setDarkMode(false);
        }
    }

    /**
     * Handles the theme switch button click event. Switches the theme between dark and light mode.
     */
    @FXML
    public void handleSwitchTheme() {
        // Setting the theme for all scenes
        SCENE_STORAGE.forEach((key, scene) -> {

            if (!key.equals("AddTask")) {
                if (appConfig.isDarkMode()) {
                    // Adding the appropriate style
                    scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/dark.css")).toExternalForm());
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/light.css")).toExternalForm());

                    // Adjust the text accordingly
                    Text text = (Text) scene.lookup("#themeSwitcherText");
                    text.setText("Too light? Switch to");

                    // Adjust the button accordingly
                    Button button = (Button) scene.lookup("#themeSwitcherBtn");
                    button.setText("dark mode");
                } else {
                    // Adding the appropriate style
                    scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/light.css")).toExternalForm());
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/dark.css")).toExternalForm());

                    // Adjust the text accordingly
                    Text text = (Text) scene.lookup("#themeSwitcherText");
                    text.setText("Too dark? Switch to");

                    // Adjust the button accordingly
                    Button button = (Button) scene.lookup("#themeSwitcherBtn");
                    button.setText("light mode");
                }
            } else {
                if (appConfig.isDarkMode()) {
                    // Adding the appropriate style
                    scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/dark.css")).toExternalForm());
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/light.css")).toExternalForm());
                } else {
                    // Adding the appropriate style
                    scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/light.css")).toExternalForm());
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/dark.css")).toExternalForm());
                }
            }
        });

        // Logging and saving the mode in the app config
        if (appConfig.isDarkMode()) {
            LOGGER.info("The application has been set to Light Mode.");
            appConfig.setDarkMode(false);
        } else {
            LOGGER.info("The application has been set to Dark Mode.");
            appConfig.setDarkMode(true);
        }
    }

    /**
     * Sets the primary stage to the given stage and attaches a handler for closing the application.
     *
     * @param stage the primary stage of the application
     */
    public void setStage(Stage stage) {
        this.stage = stage;

        // Handler for closing the application
        this.stage.setOnCloseRequest(e -> {
            LOGGER.info("The app has been stopped.");
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * Retrieves the stage object.
     *
     * @return the stage object
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Switches the scene of the stage based on the given key.
     *
     * @param key the key to identify the desired scene
     */
    public void switchScene(String key) {
        // Get the instance of the BaseController
        BaseController controller = getInstance();

        // Retrieve the stage object from the BaseController instance
        Stage stage = controller.getStage();

        // Set the scene of the stage based on the given key
        stage.setScene(controller.getScene(key));

        // Show the stage
        stage.show();

        // Log an info message indicating that the scene has been changed
        LOGGER.debug("Scene changed to '{}' scene.", key);
    }

    /**
     * Sets the scene for the given key in the scene storage, if the key does not already exist.
     *
     * @param key   the key to identify the scene
     * @param scene the scene to be set
     */
    public void putScene(String key, Scene scene) {
        // Check if the key already exists in the scene storage
        if (!SCENE_STORAGE.containsKey(key)) {
            // Add the scene to the scene storage with the given key
            SCENE_STORAGE.put(key, scene);

            // Log a debug message indicating that a new scene was added for the key
            LOGGER.debug("A new scene was added for key: " + key);
        }
    }

    /**
     * Retrieves the scene associated with the given key from the scene storage.
     *
     * @param key the key to identify the desired scene
     * @return the scene associated with the key, or null if the key is not found
     */
    public Scene getScene(String key) {
        // Retrieve the scene associated with the given key from the scene storage
        return SCENE_STORAGE.get(key);
    }

    /**
     * Removes the scene with the specified key from the SCENE_STORAGE HashMap.
     *
     * @param key the key of the scene to be removed
     */
    public void removeScene(String key) {
        // Remove the scene from the scene storage with the given key
        SCENE_STORAGE.remove(key);

        // Log a debug message indicating that the scene was removed
        LOGGER.debug("Scene '{}' has been removed.", key);
    }

    /**
     * Returns the scene storage.
     *
     * @return the scene storage
     */
    public HashMap<String, Scene> getSceneStorage() {
        return SCENE_STORAGE;
    }

    /**
     * Updates the SCENE_STORAGE HashMap with the provided updated scenes.
     *
     * @param updatedScenes the updated scenes to be stored in the SCENE_STORAGE HashMap
     */
    public void updateSceneStorage(HashMap<String, Scene> updatedScenes) {
        // Remove all existing scenes from the scene storage
        SCENE_STORAGE.clear();

        // Put the given scenes in the scene storage
        SCENE_STORAGE.putAll(updatedScenes);

        // Log a debug message indicating that the scene storage was updated
        LOGGER.debug("The scene storage has been updated.");
    }
}

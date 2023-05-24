package de.todoapp.controller;

import de.todoapp.config.AppConfig;
import de.todoapp.core.Priority;
import de.todoapp.core.State;
import de.todoapp.core.TaskManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This is an abstract base controller class that provides common functionality
 * for all controllers in the application.
 *
 * @author Tobias Metzger
 * @version 1.0
 */
public class BaseController {
    protected Stage primaryStage;

    @FXML
    protected Text themeSwitcherText;

    @FXML
    protected Button themeSwitcherBtn;

    @FXML
    protected Pane paneHeader;

    private static final Logger logger = LogManager.getLogger(BaseController.class);

    protected AppConfig appConfig = AppConfig.getInstance();


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

        // Add gaussian blur to paneHeader, as not possible via CSS
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(3);
        paneHeader.setEffect(gaussianBlur);
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
        if (appConfig.isDarkMode()) {
            themeSwitcherBtn.getScene().getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/dark.css")).toExternalForm());
            themeSwitcherBtn.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/light.css")).toExternalForm());
            themeSwitcherText.setText("Too light? Switch to");
            themeSwitcherBtn.setText("dark mode");

            // Logging and saving the mode in the app config
            logger.info("The application has been set to Light Mode.");
            appConfig.setDarkMode(false);
        } else {
            themeSwitcherBtn.getScene().getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/light.css")).toExternalForm());
            themeSwitcherBtn.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/dark.css")).toExternalForm());
            themeSwitcherText.setText("Too dark? Switch to");
            themeSwitcherBtn.setText("light mode");

            // Logging and saving the mode in the app config
            logger.info("The application has been set to Dark Mode.");
            appConfig.setDarkMode(true);
        }
    }

    /**
     * Sets the primary stage to the given stage and attaches a handler for closing the application.
     *
     * @param primaryStage the primary stage of the application
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Handler for closing the application
        this.primaryStage.setOnCloseRequest(e -> {
            logger.info("The app has been stopped.");
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * Loads the specified FXML file and switches to the corresponding scene.
     *
     * @param stage        the primary stage of the application
     * @param fxmlFileName the name of the FXML file to load
     * @param title        the title of the new scene
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    public void switchToScene(Stage stage, String fxmlFileName, String title) throws IOException {
        // Set the primary stage of the application
        setPrimaryStage(stage);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFileName)));
        Scene scene = new Scene(root);

        // Set the title of the primary stage if provided
        if (title != null) {
            primaryStage.setTitle(title);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void addTaskWithButton(ActionEvent event) throws IOException{
        TaskManager taskManager=new TaskManager();
        taskManager.addTask("task-1","example", State.IN_PROGRESS, Date.valueOf(LocalDate.now()), Priority.HIGH,10, "ShitToDo");
        logger.debug("Task added");
    }

}

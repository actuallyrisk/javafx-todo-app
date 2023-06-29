package de.todoapp.controller;

import de.todoapp.config.AppConfig;
import de.todoapp.core.*;
import de.todoapp.utils.MapUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;
import java.util.Map;
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

    private Stage stage;

    private static final Logger LOGGER = LogManager.getLogger(BaseController.class);

    private static final HashMap<String, Scene> SCENE_STORAGE = new HashMap<>();

    @FXML
    protected Text themeSwitcherText;

    @FXML
    protected Button themeSwitcherBtn;

    @FXML
    protected Pane paneHeader;

    @FXML
    protected TableView<Task> tableView;

    @FXML
    protected TableColumn<Task, String> name;

    @FXML
    protected TableColumn<Task, String> description;

    @FXML
    protected TableColumn<Task, State> state;

    @FXML
    protected TableColumn<Task, Date> dueDate;

    @FXML
    protected TableColumn<Task, Priority> priority;

    @FXML
    protected TableColumn<Task, Integer> points;

    @FXML
    protected TableColumn<Task, String> category;

    protected TaskService taskService = new TaskService();

    private Task taskToDelete = null;

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

        // Add gaussian blur to paneHeader, as not possible via CSS
        // GaussianBlur gaussianBlur = new GaussianBlur();
        // gaussianBlur.setRadius(5);
        // paneHeader.setEffect(gaussianBlur);

        // Set cell value factories for each column
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        points.setCellValueFactory(new PropertyValueFactory<>("points"));

        // Load tasks from the database
        taskService.loadFromDB();

        // Set up click event for the table view
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                taskToDelete = tableView.getSelectionModel().getSelectedItem();
            }
        });
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
        LOGGER.debug("Scene changed to " + key + " scene.");
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
     * Reloads all scenes in the SCENE_STORAGE HashMap by reloading their corresponding FXML documents.
     */
    public static void reloadScenes() {
        // Create a temporary HashMap to store the newly loaded scenes
        HashMap<String, Scene> updatedScenes = new HashMap<>();

        // Iterate over each entry (key-scene pair) in the HashMap
        for (Map.Entry<String, Scene> entry : SCENE_STORAGE.entrySet()) {
            String key = entry.getKey();
            Scene scene = entry.getValue();

            try {
                // Reload the FXML document
                Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(appConfig.getFxmlFolderPath() + key + ".fxml")));

                // Update the temporary HashMap with the newly loaded Scene
                updatedScenes.put(key, new Scene(root, scene.getWidth(), scene.getHeight()));
            } catch (IOException e) {
                LOGGER.error("Error while reloading scene: " + key);
                LOGGER.error(e.toString());
            }
        }

        // Update the original HashMap with the newly loaded scenes
        SCENE_STORAGE.clear();
        SCENE_STORAGE.putAll(updatedScenes);

        // Logs a debug message that all scenes have been reloaded
        LOGGER.debug("All scenes have been reloaded.");
    }

    /**
     * Handles the event when the add task button is clicked.
     * It opens the "AddTask" scene in a new stage, allowing the user to add a new task.
     * It also handles the automatic theme setting for the "AddTask" window based on the app configuration.
     *
     * @param event The action event generated by the button click.
     */
    @FXML
    public void addTaskWithButton(ActionEvent event) {
        Scene scene = getScene("AddTask");
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());

        // Automatic theme setting for the addTask-window
        if (appConfig.isDarkMode()) {
            scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/light.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/dark.css")).toExternalForm());
            // Saving the dark mode in the app config
            appConfig.setDarkMode(true);
        } else {
            scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/dark.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/light.css")).toExternalForm());
            // Saving the light mode in the app config
            appConfig.setDarkMode(false);
        }

        Stage stage = new Stage();

        // Modality blocks the interaction with other stages
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        // Show the stage and wait for it to be closed
        stage.showAndWait();

        taskService.loadFromDB();

        String key = MapUtils.getKeyByValue(SCENE_STORAGE, ((Node) event.getSource()).getScene());

        reloadScenes();

        switchScene(key);
    }

    /**
     * Deletes the selected task when the delete button is clicked.
     * The task is retrieved from the selected row in the table view.
     * After deleting the task, the scenes are reloaded, and the scene is switched back to the previously active scene.
     *
     * @param event the action event triggered by the delete button
     */
    @FXML
    public void deleteTaskWithButton(ActionEvent event) {
        Task selectedTask = tableView.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {
            // Delete the selected task
            taskService.deleteTask(selectedTask);

            // Get the key of the previously active scene
            String key = MapUtils.getKeyByValue(SCENE_STORAGE, ((Node) event.getSource()).getScene());

            // Reload the scenes to reflect the updated task list
            reloadScenes();

            // Switch the scene back to the previously active scene
            switchScene(key);
        }
    }

    /**
     * Sets the data in the table view with the provided list of tasks.
     *
     * @param dataList the list of tasks to be displayed in the table view
     */
    public void setTableData(ArrayList<Task> dataList) {
        // Convert the list to an observable list
        ObservableList<Task> data = FXCollections.observableArrayList(dataList);

        // Set the observable list as the data source for the table view
        tableView.setItems(data);
    }
}

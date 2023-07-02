package de.todoapp.controller;

import de.todoapp.core.Priority;
import de.todoapp.core.State;
import de.todoapp.core.Task;
import de.todoapp.core.TaskService;
import de.todoapp.utils.MapUtils;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Controller class for the Important Tasks view.
 * Extends {@link BaseController}.
 *
 * @author Tobias Metzger
 * @version 1.1
 */
public class ImportantTasksController extends BaseController {
    private static final Logger LOOGER = LogManager.getLogger(ImportantTasksController.class);

    @FXML
    private Button switchToSceneTodayButton;

    @FXML
    private Button switchToSceneScheduledButton;

    @FXML
    private Button switchToSceneAllButton;

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

    private Task taskToDelete = null;

    /**
     * Initialization logic for this controller.
     */
    @Override
    public void initialize() {
        super.initialize();

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

        ArrayList<Task> combinedTasks = new ArrayList<>();

        combinedTasks.addAll(taskService.getTaskByPriority(Priority.HIGH));
        combinedTasks.addAll(taskService.getTaskByPriority(Priority.VERY_HIGH));

        setTableData(combinedTasks);
    }

    /**
     * Event handler for switching to the "TodayTasks" scene.
     * This method is called when the corresponding button is clicked.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    public void handleSwitchToSceneTodayButton(ActionEvent event) {
        // Switch the scene to the "TodayTasks" scene
        switchScene("TodayTasks");
    }

    /**
     * Event handler for switching to the "ScheduledTasks" scene.
     * This method is called when the corresponding button is clicked.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    public void handleSwitchToSceneScheduledButton(ActionEvent event) {
        // Switch the scene to the "ScheduledTasks" scene
        switchScene("ScheduledTasks");
    }

    /**
     * Event handler for switching to the "AllTasks" scene.
     * This method is called when the corresponding button is clicked.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    public void handleSwitchToSceneAllButton(ActionEvent event) {
        // Switch the scene to the "AllTasks" scene
        switchScene("AllTasks");
    }

    @FXML
    public void handleSwitchToSceneReward(ActionEvent event) {
        // Switch the scene to the "AllTasks" scene
        switchScene("Reward");
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
        stage.setTitle(appConfig.getTitle());
        stage.setResizable(false);
        stage.setScene(scene);

        // Show the stage and wait for it to be closed
        stage.showAndWait();

        taskService.loadFromDB();

        // Get the key of the active scene
        String key = MapUtils.getKeyByValue(getSceneStorage(), ((Node) event.getSource()).getScene());

        // Reload the scenes to reflect the updated task list
        fxmlLoader.reload();

        // Switch the scene to display the new task
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
            String key = MapUtils.getKeyByValue(getSceneStorage(), ((Node) event.getSource()).getScene());

            // Reload the scenes to reflect the updated task list
            fxmlLoader.reload();

            // Switch the scene back to the previously active scene
            switchScene(key);
        }
    }

    @FXML
    public void finishTaskWithButton(ActionEvent event) {
        Task selectedTask = tableView.getSelectionModel().getSelectedItem();

        if (selectedTask != null && selectedTask.getState() != State.COMPLETED) {
            // Delete the selected task
            taskService.deleteTask(selectedTask);

            // Create a new task with the same values
            taskService.addTask(selectedTask.getName(), selectedTask.getDescription(), State.COMPLETED, new java.sql.Date(selectedTask.getDueDate().getTime()), selectedTask.getPriority(), selectedTask.getPoints(), selectedTask.getCategory());

            // Add the task points to the global points in the Database
            taskService.addUserPoints(selectedTask.getPoints());

            // Get the key of the previously active scene
            String key = MapUtils.getKeyByValue(getSceneStorage(), ((Node) event.getSource()).getScene());

            // Reload the scenes to reflect the updated task list
            fxmlLoader.reload();

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

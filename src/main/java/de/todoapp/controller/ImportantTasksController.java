package de.todoapp.controller;

import de.todoapp.core.Priority;
import de.todoapp.core.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

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

    /**
     * Initialization logic for this controller.
     */
    @Override
    public void initialize() {
        super.initialize();

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
}

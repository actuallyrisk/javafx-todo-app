package de.todoapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller class for the Scheduled Tasks view.
 * Extends {@link BaseController}.
 *
 * @author Tobias Metzger
 * @version 1.1
 */
public class ScheduledTasksController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(ScheduledTasksController.class);

    @FXML
    private Button switchToSceneTodayButton;

    @FXML
    private Button switchToSceneImportantButton;

    @FXML
    private Button switchToSceneAllButton;

    /**
     * Initialization logic for this controller.
     */
    @Override
    public void initialize() {
        super.initialize();

        setTableData(taskService.getTasksWithFutureDueDate());
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
     * Event handler for switching to the "ImportantTasks" scene.
     * This method is called when the corresponding button is clicked.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    public void handleSwitchToSceneImportantButton(ActionEvent event) {
        // Switch the scene to the "ImportantTasks" scene
        switchScene("ImportantTasks");
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

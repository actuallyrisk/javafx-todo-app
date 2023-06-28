package de.todoapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller class for the Important Tasks views.
 * Extends {@link BaseController}.
 *
 * @author Tobias Metzger
 * @version 1.1
 */
public class AllTasksController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(AllTasksController.class);

    @FXML
    private Button switchToSceneTodayButton;

    @FXML
    private Button switchToSceneScheduledButton;

    @FXML
    private Button switchToSceneImportantButton;

    /**
     * Initialization logic for this controller.
     */
    @Override
    public void initialize() {
        super.initialize();

        setTableData(taskService.getAllTasks());
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
}

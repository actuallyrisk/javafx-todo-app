package de.todoapp.controller;

import de.todoapp.core.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Important Tasks view.
 * Extends {@link BaseController}.
 *
 * @author Tobias Metzger
 * @version 1.0
 */
public class ImportantTasksController extends BaseController {

    @FXML
    private Button switchToSceneTodayButton;

    @FXML
    private Button switchToSceneScheduledButton;

    @FXML
    private Button switchToSceneAllButton;

    /**
     * Additional initialization logic for this controller.
     */
    public void initialize() {
        super.initialize();
        // ...
    }

    /**
     * Handles the button click event for switching to the "Today's Tasks" scene.
     *
     * @param event the event object
     * @throws IOException if the FXML file for the "Today's Tasks" scene cannot be loaded
     */
    @FXML
    public void handleSwitchToSceneTodayButton(ActionEvent event) throws IOException {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        switchToScene(stage, "/fxml/TodayTasks.fxml", Main.TITLE);
    }

    /**
     * Handles the button click event for switching to the "Scheduled Tasks" scene.
     *
     * @param event the event object
     * @throws IOException if the FXML file for the "Scheduled Tasks" scene cannot be loaded
     */
    @FXML
    public void handleSwitchToSceneScheduledButton(ActionEvent event) throws IOException {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        switchToScene(stage, "/fxml/ScheduledTasks.fxml", Main.TITLE);
    }

    /**
     * Handles the button click event for switching to the "All Tasks" scene.
     *
     * @param event the event object
     * @throws IOException if the FXML file for the "All Tasks" scene cannot be loaded
     */
    @FXML
    public void handleSwitchToSceneAllButton(ActionEvent event) throws IOException {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        switchToScene(stage, "/fxml/AllTasks.fxml", Main.TITLE);
    }

}

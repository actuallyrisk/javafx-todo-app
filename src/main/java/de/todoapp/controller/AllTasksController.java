package de.todoapp.controller;

import de.todoapp.core.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Controller class for the Important Tasks view.
 * Extends {@link BaseController}.
 *
 * @author Tobias Metzger
 * @version 1.0
 */
public class AllTasksController extends BaseController {
    @FXML
    private Button switchToSceneTodayButton;

    @FXML
    private Button switchToSceneScheduledButton;

    @FXML
    private Button switchToSceneImportantButton;

    private static final Logger logger = LogManager.getLogger(AllTasksController.class);

    /**
     * Initialization logic for this controller.
     */
    @Override
    public void initialize() {
        super.initialize();
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

        logger.debug("Scene changed to \"Today's Tasks\" scene.");
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

        logger.debug("Scene changed to \"Scheduled Tasks\" scene.");
    }

    /**
     * Handles the button click event for switching to the "Important Tasks" scene.
     *
     * @param event the event object
     * @throws IOException if the FXML file for the "Important Tasks" scene cannot be loaded
     */
    @FXML
    public void handleSwitchToSceneImportantButton(ActionEvent event) throws IOException {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        switchToScene(stage, "/fxml/ImportantTasks.fxml", Main.TITLE);

        logger.debug("Scene changed to \"Important Tasks\" scene.");
    }

}

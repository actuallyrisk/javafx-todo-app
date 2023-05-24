package de.todoapp.controller;

import de.todoapp.core.Main;
import de.todoapp.core.Priority;
import de.todoapp.core.State;
import de.todoapp.core.TaskManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Locale;

/**
 * Controller class for the Today Tasks view.
 * Extends {@link BaseController}.
 *
 * @author Tobias Metzger
 * @version 1.0
 */
public class TodayTasksController extends BaseController {
    @FXML
    private Button switchToSceneScheduledButton;

    @FXML
    private Button switchToSceneImportantButton;

    @FXML
    private Button switchToSceneAllButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane anchorPaneParent;

    @FXML
    private Button AddTask;

    private static final Logger logger = LogManager.getLogger(TodayTasksController.class);

    /**
     * Initialization logic for this controller.
     */
    @Override
    public void initialize() {
        super.initialize();
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

        logger.debug("Scene changed to \"All Tasks\" scene.");
    }

}

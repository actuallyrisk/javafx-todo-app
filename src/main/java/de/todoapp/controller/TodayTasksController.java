package de.todoapp.controller;

import de.todoapp.core.Task;
import de.todoapp.core.TaskService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

/**
 * Controller class for the Today Tasks views.
 * Extends {@link BaseController}.
 *
 * @author Tobias Metzger
 * @version 1.1
 */
public class TodayTasksController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(TodayTasksController.class);

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






    /**
     * Initialization logic for this controller.
     */

    @Override
    public void initialize() {




        super.initialize();
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

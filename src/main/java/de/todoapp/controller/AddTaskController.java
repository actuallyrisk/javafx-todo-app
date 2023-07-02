package de.todoapp.controller;

import de.todoapp.core.Priority;
import de.todoapp.core.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.todoapp.core.TaskService;

import java.sql.Date;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Controller class for the Add Task view.
 *
 * @author Luis Kronenbitter
 * @version 1.0
 */
public class AddTaskController {
    private static final Logger LOGGER = LogManager.getLogger(AddTaskController.class);

    @FXML
    private TextField textName;

    @FXML
    private TextField textDescription;

    @FXML
    private TextField textCategory;

    @FXML
    private ChoiceBox<State> choiceBoxStatus;

    @FXML
    private ChoiceBox<Priority> choiceBoxPriority;

    @FXML
    private DatePicker datePickerDueDate;

    @FXML
    private Label errorLabel;

    /**
     * Initialization logic for this controller.
     */
    public void initialize() {
        ObservableList<State> state = FXCollections.observableArrayList(State.values());
        choiceBoxStatus.setItems(state);

        ObservableList<Priority> priority = FXCollections.observableArrayList(Priority.values());
        choiceBoxPriority.setItems(priority);

        errorLabel.setVisible(false);
    }

    /**
     * Cancels the action and closes the current stage.
     *
     * @param event the action event triggered by the cancel button
     */
    @FXML
    public void cancel(ActionEvent event) {
        // Hide the error label
        errorLabel.setVisible(false);

        // Close the current stage
        closeStage(event);
    }

    /**
     * Finishes the action and performs the necessary operations based on the user input.
     *
     * @param event the action event triggered by the finish button
     */
    @FXML
    public void finish(ActionEvent event) {
        String name = textName.getText() == null ? "" : textName.getText();
        String description = textDescription.getText() == null ? "" : textDescription.getText();
        String category = textCategory.getText() == null ? "" : textCategory.getText();
        State choiceStatus = choiceBoxStatus.getValue();
        Priority choicePriority = choiceBoxPriority.getValue();
        Date dueDate = datePickerDueDate.getValue() == null ? null : Date.valueOf(datePickerDueDate.getValue());

        if (name.isEmpty() || description.isEmpty() || category.isEmpty() || choicePriority == null || choiceStatus == null || dueDate == null) {
            // Display the error label
            errorLabel.setVisible(true);

            // Consume the event to prevent further processing
            event.consume();
        } else {
            TaskService taskService = new TaskService();

            taskService.addTask(name, description, choiceStatus, dueDate, choicePriority, (int) (Math.random() * 25) + 1, category);
            clearAddTaskWindow();

            // Close the current stage
            closeStage(event);
        }
    }

    /**
     * Closes the current stage and performs necessary cleanup.
     *
     * @param event the action event triggered by the close button
     */
    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Clear the add task window fields
        clearAddTaskWindow();

        // Close the current stage
        stage.close();
    }

    /**
     * Clears the fields of the add task window.
     * This method is called to reset the input fields after completing an action.
     */
    private void clearAddTaskWindow() {
        // Clear the task name field
        textName.clear();

        // Clear the task description field
        textDescription.clear();

        // Reset the selected priority value
        choiceBoxPriority.setValue(null);

        // Reset the selected status value
        choiceBoxStatus.setValue(null);
    }
}

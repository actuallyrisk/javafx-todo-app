package de.todoapp.controller;

import de.todoapp.core.Priority;
import de.todoapp.core.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.todoapp.core.TaskService;
import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;



public class AddTaskController {

    private static final Logger LOGGER = LogManager.getLogger(AddTaskController.class);

    @FXML
    private TextField textName;

    @FXML
    private TextField textDescription;

    @FXML
    private ChoiceBox<State> choiceBoxStatus;

    @FXML
    private ChoiceBox<Priority> choiceBoxPriority;

    @FXML
    private  Label errorLabel;

    String name, description;
    State choiceStatus;
    Priority choicePriority;


    /**
     * initializes the AddTaskController:
     * -the choiceboxes are filled with the enums "priority" and "state"
     */
    public void initialize(){
        ObservableList<State> state = FXCollections.observableArrayList(State.values());
        choiceBoxStatus.setItems(state);

        ObservableList<Priority> priority = FXCollections.observableArrayList(Priority.values());
        choiceBoxPriority.setItems(priority);

        errorLabel.setVisible(false);

        }




    /**
     * @param e parameter for eventhandler
     * closes the "add Task" window without sending any data to the backend
     */
    @FXML
    public void cancel(ActionEvent e){
        errorLabel.setVisible(false);
        closeStage(e);
    }

    /**
     * @param e parameter for eventhandler
     * when the button "finish" is clicked:
     *         - sends the input data to the backend/ adding a task in the database
     *          -closing the window
     *          -Checks the Userinput, and blocks the process, if the fields are not properly filled
     */
    @FXML
    public void finish(ActionEvent e){

            name=textName.getText();
            description=textDescription.getText();
            choiceStatus=choiceBoxStatus.getValue();
            choicePriority=choiceBoxPriority.getValue();

            if (name.equals(null) || description.equals(null) || choicePriority == null || choiceStatus == null) {
                clearAddTaskWindow();
                errorLabel.setVisible(true);
                e.consume();
            }
            else {

                TaskService task = new TaskService();
                Date date = Date.valueOf(LocalDate.now());

                task.addTask(name, description, choiceStatus, date, choicePriority, 10, "random");
                clearAddTaskWindow();
                closeStage(e);
            }
    }

    /**
     * helper function for the actions triggered by the finish button/ necessary for closing the "add Task" window
     * @param event parameter for eventhandler
     */
    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        clearAddTaskWindow();
        stage.close();
    }
    private void clearAddTaskWindow(){
        textName.clear();
        textDescription.clear();
        choiceBoxPriority.setValue(null);
        choiceBoxStatus.setValue(null);
    }


}

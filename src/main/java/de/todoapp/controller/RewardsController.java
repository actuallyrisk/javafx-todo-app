package de.todoapp.controller;

import de.todoapp.reward.BaseReward;
import de.todoapp.reward.RewardManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class RewardsController extends BaseController {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label displayPoints;

    @Override
    public void initialize() {
        super.initialize();
        setReachedPoints();
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

    public void setReachedPoints() {
        int points = taskService.getUserPoints();
        RewardManager rewardManager = new RewardManager();

        ArrayList<BaseReward> rewards = rewardManager.getGainedRewards(taskService.getUserPoints());

        for (BaseReward reward : rewards) {
            String rewardId = reward.getId();
            Node node = gridPane.lookup("#" + rewardId);
            if (node != null) {
                node.setStyle("-fx-opacity: 1 !important;");
            }
        }

        if (points != 0) {
            displayPoints.setText(Integer.toString(points));
        }
    }
}

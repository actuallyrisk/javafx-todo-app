package de.todoapp.reward;

import javafx.scene.image.Image;

public class GoalMasterTrophy extends BaseReward {

    public GoalMasterTrophy() {
        image = new Image("images/rewards/GoalMasterTrophy.png");
        name = "Goal Master Trophy";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 100) {
            return true;
        }
        return false;
    }
}


package de.todoapp.reward;

import javafx.scene.image.Image;

public class VictoryTrophy extends BaseReward {

    public VictoryTrophy() {
        image = new Image("images/rewards/VictoryTrophy.png");
        name = "Victory Trophy";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 350) {
            return true;
        }
        return false;
    }
}


package de.todoapp.reward;

import javafx.scene.image.Image;

public class MotivationMedal extends BaseReward {

    public MotivationMedal() {
        image = new Image("images/rewards/MotivationMedal.png");
        name = "Motivation Medal";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 50) {
            return true;
        }
        return false;
    }
}


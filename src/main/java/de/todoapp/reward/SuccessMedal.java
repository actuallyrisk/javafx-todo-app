package de.todoapp.reward;

import javafx.scene.image.Image;

public class SuccessMedal extends BaseReward {

    public SuccessMedal() {
        image = new Image("images/rewards/SuccessMedal.png");
        name = "Success Medal";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 300) {
            return true;
        }
        return false;
    }
}


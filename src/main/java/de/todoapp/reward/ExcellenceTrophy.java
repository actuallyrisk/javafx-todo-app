package de.todoapp.reward;

import javafx.scene.image.Image;

public class ExcellenceTrophy extends BaseReward {

    public ExcellenceTrophy() {
        image = new Image("images/rewards/ExcellenceTrophy.png");
        name = "Excellence Trophy";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 175) {
            return true;
        }
        return false;
    }
}


package de.todoapp.reward;

import javafx.scene.image.Image;

public class SilverMedal extends BaseReward {

    public SilverMedal() {
        image = new Image("images/rewards/SilverMedal.png");
        name = "Silver Medal";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 250) {
            return true;
        }
        return false;
    }
}


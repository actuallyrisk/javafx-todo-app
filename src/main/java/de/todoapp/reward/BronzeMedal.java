package de.todoapp.reward;

import javafx.scene.image.Image;

public class BronzeMedal extends BaseReward {

    public BronzeMedal() {
        image = new Image("images/rewards/BronzeMedal.png");
        name = "Bronze Medal";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 200) {
            return true;
        }
        return false;
    }
}


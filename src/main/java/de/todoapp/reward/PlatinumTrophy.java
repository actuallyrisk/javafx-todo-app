package de.todoapp.reward;

import javafx.scene.image.Image;

public class PlatinumTrophy extends BaseReward {

    public PlatinumTrophy() {
        image = new Image("images/rewards/PlatinumTrophy.png");
        name = "Platinum Trophy";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 550) {
            return true;
        }
        return false;
    }
}


package de.todoapp.reward;

import javafx.scene.image.Image;

public class DiamondTrophy extends BaseReward {

    public DiamondTrophy() {
        image = new Image("images/rewards/DiamondTrophy.png");
        name = "Diamond Trophy";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 600) {
            return true;
        }
        return false;
    }
}


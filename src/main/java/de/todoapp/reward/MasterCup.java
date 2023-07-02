package de.todoapp.reward;

import javafx.scene.image.Image;

public class MasterCup extends BaseReward {

    public MasterCup() {
        image = new Image("images/rewards/MasterCup.png");
        name = "Master Cup";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 250) {
            return true;
        }
        return false;
    }
}


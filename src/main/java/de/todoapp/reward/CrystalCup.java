package de.todoapp.reward;

import javafx.scene.image.Image;

public class CrystalCup extends BaseReward {

    public CrystalCup() {
        image = new Image("images/rewards/CrystalCup.png");
        name = "Crystal Cup";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 500) {
            return true;
        }
        return false;
    }
}


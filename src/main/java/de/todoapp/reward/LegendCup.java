package de.todoapp.reward;

import javafx.scene.image.Image;

public class LegendCup extends BaseReward {

    public LegendCup() {
        image = new Image("images/rewards/LegendCup.png");
        name = "Legend Cup";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 225) {
            return true;
        }
        return false;
    }
}


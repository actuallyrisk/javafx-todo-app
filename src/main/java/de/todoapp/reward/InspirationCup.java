package de.todoapp.reward;

import javafx.scene.image.Image;

public class InspirationCup extends BaseReward {

    public InspirationCup() {
        image = new Image("images/rewards/InspirationCup.png");
        name = "Inspiration Cup";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 125) {
            return true;
        }
        return false;
    }
}


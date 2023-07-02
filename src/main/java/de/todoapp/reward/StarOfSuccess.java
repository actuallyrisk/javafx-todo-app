package de.todoapp.reward;

import javafx.scene.image.Image;

public class StarOfSuccess extends BaseReward {

    public StarOfSuccess() {
        image = new Image("images/rewards/StarOfSuccess.png");
        name = "Star of Success";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 275) {
            return true;
        }
        return false;
    }
}


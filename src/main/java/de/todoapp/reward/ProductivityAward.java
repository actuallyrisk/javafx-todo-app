package de.todoapp.reward;

import javafx.scene.image.Image;

public class ProductivityAward extends BaseReward {

    public ProductivityAward() {
        image = new Image("images/rewards/ProductivityAward.png");
        name = "Productivity Award";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 75) {
            return true;
        }
        return false;
    }
}


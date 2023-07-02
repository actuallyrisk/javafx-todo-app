package de.todoapp.reward;

import de.todoapp.core.Main;
import javafx.scene.image.Image;

import de.todoapp.core.*;

public class GoldenCup extends BaseReward {

    public GoldenCup() {
        image = new Image("images/rewards/GoldenCup.png");
        name = "Golden Cup";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 300) {
            return true;
        }
        return false;
    }
}

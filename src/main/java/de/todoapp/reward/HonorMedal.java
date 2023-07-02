package de.todoapp.reward;

import javafx.scene.image.Image;

public class HonorMedal extends BaseReward {

    public HonorMedal() {
        image = new Image("images/rewards/HonorMedal.png");
        name = "Honor Medal";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 200) {
            return true;
        }
        return false;
    }
}


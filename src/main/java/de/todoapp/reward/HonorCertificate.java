package de.todoapp.reward;

import javafx.scene.image.Image;

public class HonorCertificate extends BaseReward {

    public HonorCertificate() {
        image = new Image("images/rewards/HonorCertificate.png");
        name = "Honor Certificate";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 450) {
            return true;
        }
        return false;
    }
}


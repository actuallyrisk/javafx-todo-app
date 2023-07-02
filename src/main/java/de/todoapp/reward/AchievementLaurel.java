package de.todoapp.reward;

import javafx.scene.image.Image;

public class AchievementLaurel extends BaseReward {

    public AchievementLaurel() {
        image = new Image("images/rewards/AchievementLaurel.png");
        name = "Achievement Laurel";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 150) {
            return true;
        }
        return false;
    }
}


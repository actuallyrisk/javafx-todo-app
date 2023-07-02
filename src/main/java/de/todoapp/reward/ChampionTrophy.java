package de.todoapp.reward;

import javafx.scene.image.Image;

public class ChampionTrophy extends BaseReward {

    public ChampionTrophy() {
        image = new Image("images/rewards/ChampionTrophy.png");
        name = "Champion Trophy";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 400) {
            return true;
        }
        return false;
    }
}


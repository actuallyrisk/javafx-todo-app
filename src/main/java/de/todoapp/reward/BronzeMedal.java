package de.todoapp.reward;

public class BronzeMedal extends BaseReward {

    public BronzeMedal() {
        id = "bronzeMedal";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 200) {
            return true;
        }
        return false;
    }
}


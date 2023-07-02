package de.todoapp.reward;

public class LegendCup extends BaseReward {

    public LegendCup() {
        id = "legendCup";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 225) {
            return true;
        }
        return false;
    }
}


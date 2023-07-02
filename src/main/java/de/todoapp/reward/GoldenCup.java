package de.todoapp.reward;

public class GoldenCup extends BaseReward {

    public GoldenCup() {
        id = "goldenCup";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 300) {
            return true;
        }
        return false;
    }
}

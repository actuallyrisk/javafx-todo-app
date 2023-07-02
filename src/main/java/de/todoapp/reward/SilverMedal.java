package de.todoapp.reward;

public class SilverMedal extends BaseReward {

    public SilverMedal() {
        id = "silverMedal";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 250) {
            return true;
        }
        return false;
    }
}


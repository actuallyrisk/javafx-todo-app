package de.todoapp.reward;

public class MotivationMedal extends BaseReward {

    public MotivationMedal() {
        id = "motivationMedal";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 50) {
            return true;
        }
        return false;
    }
}


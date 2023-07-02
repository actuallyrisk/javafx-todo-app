package de.todoapp.reward;

public class SuccessMedal extends BaseReward {

    public SuccessMedal() {
        id = "successMedal";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 300) {
            return true;
        }
        return false;
    }
}


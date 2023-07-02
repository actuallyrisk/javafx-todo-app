package de.todoapp.reward;

public class HonorMedal extends BaseReward {

    public HonorMedal() {
        id = "honorMedal";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 200) {
            return true;
        }
        return false;
    }
}


package de.todoapp.reward;

public class ExcellenceTrophy extends BaseReward {

    public ExcellenceTrophy() {
        id = "excellenceTrophy";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 175) {
            return true;
        }
        return false;
    }
}


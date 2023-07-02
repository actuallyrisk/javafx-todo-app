package de.todoapp.reward;

public class InspirationCup extends BaseReward {

    public InspirationCup() {
        id = "inspirationCup";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 125) {
            return true;
        }
        return false;
    }
}


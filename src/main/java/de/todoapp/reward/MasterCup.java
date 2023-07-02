package de.todoapp.reward;

public class MasterCup extends BaseReward {

    public MasterCup() {
        id = "masterCup";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 250) {
            return true;
        }
        return false;
    }
}


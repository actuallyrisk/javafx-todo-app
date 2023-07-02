package de.todoapp.reward;

public class CrystalCup extends BaseReward {

    public CrystalCup() {
        id = "crystalCup";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 500) {
            return true;
        }
        return false;
    }
}


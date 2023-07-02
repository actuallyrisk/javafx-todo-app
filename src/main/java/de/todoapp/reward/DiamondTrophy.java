package de.todoapp.reward;

public class DiamondTrophy extends BaseReward {

    public DiamondTrophy() {
        id = "diamondTrophy";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 600) {
            return true;
        }
        return false;
    }
}


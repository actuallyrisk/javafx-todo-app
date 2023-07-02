package de.todoapp.reward;

public class VictoryTrophy extends BaseReward {

    public VictoryTrophy() {
        id = "victoryTrophy";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 350) {
            return true;
        }
        return false;
    }
}


package de.todoapp.reward;

public class ChampionTrophy extends BaseReward {

    public ChampionTrophy() {
        id = "championTrophy";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 400) {
            return true;
        }
        return false;
    }
}


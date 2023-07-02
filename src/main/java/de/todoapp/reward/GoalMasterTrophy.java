package de.todoapp.reward;


public class GoalMasterTrophy extends BaseReward {

    public GoalMasterTrophy() {
        id = "goalMasterTrophy";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 100) {
            return true;
        }
        return false;
    }
}


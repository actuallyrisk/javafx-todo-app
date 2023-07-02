package de.todoapp.reward;

public class StarOfSuccess extends BaseReward {

    public StarOfSuccess() {
        id = "starOfSuccess";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 275) {
            return true;
        }
        return false;
    }
}


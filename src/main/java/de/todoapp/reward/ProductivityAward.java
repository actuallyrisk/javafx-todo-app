package de.todoapp.reward;

public class ProductivityAward extends BaseReward {

    public ProductivityAward() {
        id = "productivityAward";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 75) {
            return true;
        }
        return false;
    }
}


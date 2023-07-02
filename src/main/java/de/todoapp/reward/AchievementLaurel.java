package de.todoapp.reward;

public class AchievementLaurel extends BaseReward {

    public AchievementLaurel() {
        id = "achievementLaurel";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 150) {
            return true;
        }
        return false;
    }
}


package de.todoapp.reward;

import java.util.ArrayList;

/**
 * The RewardManager class manages the rewards in the ToDo App.
 * It keeps track of all available rewards and provides methods to retrieve gained rewards based on the points earned.
 *
 * @Author Anton Horn
 */
public class RewardManager {

    private ArrayList<BaseReward> allRewards;

    /**
     * Constructs a RewardManager object and initializes the list of all rewards.
     */
    public RewardManager() {
        allRewards = new ArrayList<>();
        allRewards.add(RewardFactory.createReward("GoldenCup"));
        allRewards.add(RewardFactory.createReward("SilverMedal"));
        allRewards.add(RewardFactory.createReward("BronzeMedal"));
        allRewards.add(RewardFactory.createReward("DiamondTrophy"));
        allRewards.add(RewardFactory.createReward("CrystalCup"));
        allRewards.add(RewardFactory.createReward("HonorCertificate"));
        allRewards.add(RewardFactory.createReward("ChampionTrophy"));
        allRewards.add(RewardFactory.createReward("VictoryTrophy"));
        allRewards.add(RewardFactory.createReward("SuccessMedal"));
        allRewards.add(RewardFactory.createReward("StarOfSuccess"));
        allRewards.add(RewardFactory.createReward("MasterCup"));
        allRewards.add(RewardFactory.createReward("LegendCup"));
        allRewards.add(RewardFactory.createReward("HonorMedal"));
        allRewards.add(RewardFactory.createReward("ExcellenceTrophy"));
        allRewards.add(RewardFactory.createReward("AchievementLaurel"));
        allRewards.add(RewardFactory.createReward("InspirationCup"));
        allRewards.add(RewardFactory.createReward("GoalMasterTrophy"));
        allRewards.add(RewardFactory.createReward("ProductivityAward"));
        allRewards.add(RewardFactory.createReward("MotivationMedal"));
    }

    /**
     * Retrieves the list of gained rewards based on the points earned.
     *
     * @param points The total points earned.
     * @return The list of gained rewards.
     */
    public ArrayList<BaseReward> getGainedRewards(int points) {
        ArrayList<BaseReward> result = new ArrayList<>();
        for (BaseReward reward : allRewards) {
            if (reward.gainedReward(points)) {
                result.add(reward);
            }
        }
        return result;
    }

}


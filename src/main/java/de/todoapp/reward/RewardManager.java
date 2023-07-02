package de.todoapp.reward;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RewardManager {

    private ArrayList<BaseReward> allRewards;

    public RewardManager() {
        allRewards = new ArrayList<>();
        allRewards.add(RewardFactory.createReward("GoldenCup"));
        allRewards.add(RewardFactory.createReward("SilverMedal"));
        allRewards.add(RewardFactory.createReward("BronzeMedal"));
        allRewards.add(RewardFactory.createReward("DiamondTrophy"));
        allRewards.add(RewardFactory.createReward("PlatinumTrophy"));
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
        allRewards.add(RewardFactory.createReward("EnduranceAccolade"));
    }


    public ArrayList<BaseReward> getGainedRewards(int points) {
        ArrayList<BaseReward> result = new ArrayList<>();
        for (BaseReward reward: allRewards) {

            if (reward.gainedReward(points)) {
                result.add(reward);
            }

        }
        return result;
    }

}

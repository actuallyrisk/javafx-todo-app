package de.todoapp.reward;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class RewardFactory {

    public static BaseReward createReward(String name) {
        switch (name) {
            case "GoldenCup":
                System.out.println("Congratulations! You have won the Golden Cup!");
                return new GoldenCup();
            case "SilverMedal":
                System.out.println("Congratulations! You have won the Silver Medal!");
                return new SilverMedal();
            case "BronzeMedal":
                System.out.println("Congratulations! You have won the Bronze Medal!");
                return new BronzeMedal();
            case "DiamondTrophy":
                System.out.println("Congratulations! You have won the Diamond Trophy!");
                return new DiamondTrophy();
            case "PlatinumTrophy":
                System.out.println("Congratulations! You have won the Platinum Trophy!");
                return new PlatinumTrophy();
            case "CrystalCup":
                System.out.println("Congratulations! You have won the Crystal Cup!");
                return new CrystalCup();
            case "HonorCertificate":
                System.out.println("Congratulations! You have won the Honor Certificate!");
                return new HonorCertificate();
            case "ChampionTrophy":
                System.out.println("Congratulations! You have won the Champion Trophy!");
                return new ChampionTrophy();
            case "VictoryTrophy":
                System.out.println("Congratulations! You have won the Victory Trophy!");
                return new VictoryTrophy();
            case "SuccessMedal":
                System.out.println("Congratulations! You have won the Success Medal!");
                return new SuccessMedal();
            case "StarOfSuccess":
                System.out.println("Congratulations! You have won the Star of Success!");
                return new StarOfSuccess();
            case "MasterCup":
                System.out.println("Congratulations! You have won the Master Cup!");
                return new MasterCup();
            case "LegendCup":
                System.out.println("Congratulations! You have won the Legend Cup!");
                return new LegendCup();
            case "HonorMedal":
                System.out.println("Congratulations! You have won the Honor Medal!");
                return new HonorMedal();
            case "ExcellenceTrophy":
                System.out.println("Congratulations! You have won the Excellence Trophy!");
                return new ExcellenceTrophy();
            case "AchievementLaurel":
                System.out.println("Congratulations! You have won the Achievement Laurel!");
                return new AchievementLaurel();
            case "InspirationCup":
                System.out.println("Congratulations! You have won the Inspiration Cup!");
                return new InspirationCup();
            case "GoalMasterTrophy":
                System.out.println("Congratulations! You have won the Goal Master Trophy!");
                return new GoalMasterTrophy();
            case "ProductivityAward":
                System.out.println("Congratulations! You have won the Productivity Award!");
                return new ProductivityAward();
            case "MotivationMedal":
                System.out.println("Congratulations! You have won the Motivation Medal!");
                return new MotivationMedal();
            default:
                System.out.println("Invalid Trophy!");
                // Code für den Fall, dass der Name nicht erkannt wird
                return null;
        }
    }


}

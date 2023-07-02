package de.todoapp.reward;

import de.todoapp.core.Main;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * The RewardFactory class is responsible for creating reward objects based on the given reward name.
 * It provides a static method to create rewards.
 * Each reward is created as an instance of the corresponding subclass of the BaseReward class.
 *
 * @Author Anton Horn
 */
public class RewardFactory {
    private static final Logger LOGGER = LogManager.getLogger(RewardFactory.class);

    /**
     * Creates a reward object based on the given reward name.
     *
     * @param name The name of the reward.
     * @return The created reward object.
     */
    public static BaseReward createReward(String name) {
        switch (name) {
            case "GoldenCup":
                return new GoldenCup();
            case "SilverMedal":
                return new SilverMedal();
            case "BronzeMedal":
                return new BronzeMedal();
            case "DiamondTrophy":
                return new DiamondTrophy();
            case "PlatinumTrophy":
                return new PlatinumTrophy();
            case "CrystalCup":
                return new CrystalCup();
            case "HonorCertificate":
                return new HonorCertificate();
            case "ChampionTrophy":
                return new ChampionTrophy();
            case "VictoryTrophy":
                return new VictoryTrophy();
            case "SuccessMedal":
                return new SuccessMedal();
            case "StarOfSuccess":
                return new StarOfSuccess();
            case "MasterCup":
                return new MasterCup();
            case "LegendCup":
                return new LegendCup();
            case "HonorMedal":
                return new HonorMedal();
            case "ExcellenceTrophy":
                return new ExcellenceTrophy();
            case "AchievementLaurel":
                return new AchievementLaurel();
            case "InspirationCup":
                return new InspirationCup();
            case "GoalMasterTrophy":
                return new GoalMasterTrophy();
            case "ProductivityAward":
                return new ProductivityAward();
            case "MotivationMedal":
                return new MotivationMedal();
            default:
                LOGGER.error("Incorrect reward name!");
                return null;
        }
    }

}


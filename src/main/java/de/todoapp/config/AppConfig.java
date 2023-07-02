package de.todoapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jthemedetecor.OsThemeDetector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * This class represents the application configuration.
 * It manages the application settings and provides methods to load and save the configuration.
 *
 * @author Tomislav Zecevic, Tobias Metzger
 * @version 1.0
 */
public class AppConfig {
    private static final Logger LOGGER = LogManager.getLogger(AppConfig.class);

    // Path to the configuration file
    private static final String CONFIG_FILE_PATH = "config.json";

    private static AppConfig instance;

    // Application settings
    private boolean darkMode;
    private double width;
    private double height;
    private String title;
    private String fxmlFolderPath;

    // Object mapper for JSON serialization and deserialization
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private AppConfig() {
        // Private constructor to enforce singleton pattern
    }

    /**
     * Returns the instance of AppConfig (singleton pattern).
     *
     * @return The instance of AppConfig
     */
    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    /**
     * Checks if the dark mode is enabled.
     *
     * @return true if dark mode is enabled, false otherwise
     */
    public boolean isDarkMode() {
        return darkMode;
    }

    /**
     * Sets the dark mode.
     * Saves the configuration after updating the dark mode setting.
     *
     * @param darkMode true to enable dark mode, false to disable it
     */
    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
        saveConfig();
    }

    /**
     * Returns the width of the application window.
     *
     * @return The width of the application window
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the application window.
     * Saves the configuration after updating the width setting.
     *
     * @param width The width of the application window
     */
    public void setWidth(double width) {
        this.width = width;
        saveConfig();
    }

    /**
     * Returns the height of the application window.
     *
     * @return The height of the application window
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the application window.
     * Saves the configuration after updating the height setting.
     *
     * @param height The height of the application window
     */
    public void setHeight(double height) {
        this.height = height;
        saveConfig();
    }

    /**
     * Returns the title of the application.
     *
     * @return The title of the application
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the application.
     * Saves the configuration after updating the title.
     *
     * @param title The title of the application
     */
    public void setTitle(String title) {
        this.title = title;
        saveConfig();
    }

    /**
     * Returns the folder path where the application FXML files are located.
     *
     * @return The folder path of the FXML files
     */
    public String getFxmlFolderPath() {
        return fxmlFolderPath;
    }

    /**
     * Sets the folder path where the application FXML files are located.
     * Saves the configuration after updating the folder path.
     *
     * @param fxmlFolderPath The folder path of the FXML files
     */
    public void setFxmlFolderPath(String fxmlFolderPath) {
        this.fxmlFolderPath = fxmlFolderPath;
        saveConfig();
    }

    /**
     * Loads the configuration from the configuration file.
     * If the file exists, it loads the configuration values.
     * If the file does not exist, it initializes with default values and saves the configuration.
     */
    public void loadConfig() {
        try {
            File configFile = new File(CONFIG_FILE_PATH);
            if (configFile.exists()) {
                // If the config file exists, load the configuration from it
                AppConfig config = objectMapper.readValue(configFile, AppConfig.class);
                copyProperties(config);
            } else {
                // If the config file doesn't exist, initialize with default values
                initializeWithDefaultValues();
                saveConfig();
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load configuration from file: {}", e.getMessage());
        }
    }

    /**
     * Saves the configuration to the configuration file.
     */
    private void saveConfig() {
        try {
            objectMapper.writeValue(new File(CONFIG_FILE_PATH), this);
        } catch (IOException e) {
            LOGGER.error("Failed to save configuration to file: {}", e.getMessage());
        }
    }

    /**
     * Copies the properties from the given AppConfig object.
     *
     * @param config The AppConfig object to copy properties from
     */
    private void copyProperties(AppConfig config) {
        this.darkMode = config.darkMode;
        this.width = config.width;
        this.height = config.height;
        this.title = config.title;
        this.fxmlFolderPath = config.fxmlFolderPath;
    }

    /**
     * Initializes the configuration with default values.
     */
    private void initializeWithDefaultValues() {
        this.darkMode = OsThemeDetector.getDetector().isDark();
        this.width = 860.0;
        this.height = 560.0;
        this.title = "To-Do Desktop";
        this.fxmlFolderPath = "/views/";
    }
}

package de.todoapp.config;

/**
 * The AppConfig class represents the configuration settings for the application.
 * It provides access to various configuration options, such as the dark mode setting.
 *
 * @author Tobias Metzger
 * @version 1.0
 */
public class AppConfig {
    private static AppConfig instance;
    private boolean darkMode = false;

    /**
     * Private constructor to restrict the instantiation of AppConfig class from other classes.
     * This ensures that AppConfig follows the Singleton pattern and only one instance of AppConfig can exist.
     */
    private AppConfig() {}

    /**
     * Returns the singleton instance of the AppConfig class.
     *
     * @return The singleton instance of AppConfig.
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
     * @return {@code true} if dark mode is enabled, {@code false} otherwise.
     */
    public boolean isDarkMode() {
        return darkMode;
    }

    /**
     * Sets the dark mode setting.
     *
     * @param darkMode {@code true} to enable dark mode, {@code false} to disable it.
     */
    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }
}

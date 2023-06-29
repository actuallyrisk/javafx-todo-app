package de.todoapp.config;

import com.jthemedetecor.OsThemeDetector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The AppConfig class represents the configuration settings for the application.
 * It provides access to various configuration options, such as the dark mode setting.
 *
 * @author Tomislav Zecevic, Tobias Metzger
 * @version 1.0
 */
public class AppConfig {
    private static AppConfig instance;

    private boolean darkMode = true;

    private double width = 860.;

    private double height = 560.;

    private String title = "To-Do Desktop";

    private String fxmlFolderPath = "/views/";

    private static final Logger LOGGER = LogManager.getLogger(AppConfig.class);

    /**
     * Private constructor to restrict the instantiation of AppConfig class from other classes.
     * This ensures that AppConfig follows the Singleton pattern and only one instance of AppConfig can exist.
     */
    private AppConfig() {
        // Get OS theme
        final OsThemeDetector detector = OsThemeDetector.getDetector();
        final boolean isDarkThemeUsed = detector.isDark();

        // Set the theme accordingly
        if (isDarkThemeUsed) {
            setDarkMode(true);
            LOGGER.info("The application has been set to Dark Mode.");
        } else {
            setDarkMode(false);
            LOGGER.info("The application has been set to Light Mode.");
        }
    }

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

    /**
     * Returns the width of the application window.
     *
     * @return the width of the application window
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the application window.
     *
     * @param width the width of the application window
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Returns the height of the application window.
     *
     * @return the height of the application window
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the application window.
     *
     * @param height the height of the application window
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Returns the title of the application.
     *
     * @return the title of the application
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the application.
     *
     * @param title the title of the application
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the folder path where the FXML files are located.
     *
     * @return the folder path where the FXML files are located
     */
    public String getFxmlFolderPath() {
        return fxmlFolderPath;
    }

    /**
     * Sets the folder path where the FXML files are located.
     *
     * @param fxmlFolderPath the folder path where the FXML files are located
     */
    public void setFxmlFolderPath(String fxmlFolderPath) {
        this.fxmlFolderPath = fxmlFolderPath;
    }
}

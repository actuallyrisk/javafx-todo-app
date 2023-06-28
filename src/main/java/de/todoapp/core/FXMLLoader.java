package de.todoapp.core;

import de.todoapp.controller.BaseController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FXMLLoader implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(FXMLLoader.class);

    /**
     * Retrieves a key from a given file name by removing the file extension.
     *
     * @param fileName the name of the file
     * @return the key extracted from the file name
     */
    private static String getKeyFromFileName(String fileName) {
        // Create a Path object based on the given file name
        Path filePath = Paths.get(fileName);

        // Get the file name as a string
        String key = filePath.getFileName().toString();

        // Find the last index of the dot (file extension separator)
        int dotIndex = key.lastIndexOf(".");

        // If the dot is found and it's not the first character, remove the file extension
        if (dotIndex > 0) {
            key = key.substring(0, dotIndex);
        }

        // Return the extracted key
        return key;
    }

    /**
     * Retrieves a list of filenames in the specified resource path.
     *
     * @param path the path to the resource folder or directory
     * @return an array of filenames in the resource folder or directory
     * @throws IOException if an I/O error occurs while accessing the resource
     */
    private static String[] getResourceListing(String path) throws IOException {
        // Create a File object for the specified resource path
        File folder = new File(Objects.requireNonNull(Main.class.getResource(path)).getFile());

        // Return the list of filenames in the resource folder or directory
        return folder.list();
    }

    /**
     * Loads the FXML files and creates scenes for each file.
     *
     * @param fxmlFolderPath the path to the folder containing the FXML files
     */

    public static String[] loadFxmlFiles(String fxmlFolderPath, double width, double height) {
        try {
            // Get a list of FXML files in the specified folder
            String[] fxmlFiles = getResourceListing(fxmlFolderPath);

            // Iterate over each FXML file
            for (String fxmlFile : fxmlFiles) {
                // Load the FXML document using FXMLLoader and create a Parent component
                Parent root = javafx.fxml.FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlFolderPath + fxmlFile)));

                //with the "AddTask.fxml" file will be a deviating scene created, with a different size compared to the other scenes
                //the scene will be also put on the scene hashmap
                if(fxmlFile.equals("AddTask.fxml")) {
                    BaseController.getInstance().putScene(getKeyFromFileName(fxmlFile), new Scene(root, 302, 289));
                }
                else {
                    // Set the scene with the root component and the specified width and height
                    BaseController.getInstance().putScene(getKeyFromFileName(fxmlFile), new Scene(root, width, height));
                }


                // Logs a debug message indicating that all views files have been loaded.
                LOGGER.info("All fxml files have been loaded!");

            }
            return fxmlFiles;
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        return new String[]{};
    }

    private double width, height;
    private String filepath;

    public FXMLLoader(double width, double height, String filepath) {
        this.width = width;
        this.height = height;
        this.filepath = filepath;
    }

    @Override
    public void run() {

        loadFxmlFiles(filepath, width, height);

    }



}

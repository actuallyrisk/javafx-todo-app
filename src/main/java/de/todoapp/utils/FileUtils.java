package de.todoapp.utils;

import de.todoapp.core.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Utility class for file-related operations.
 *
 * @author Tobias Metzger
 * @version 1.0
 */
public class FileUtils {

    /**
     * Retrieves a key from a given file name by removing the file extension.
     *
     * @param fileName the name of the file
     * @return the key extracted from the file name
     */
    public static String getKeyFromFileName(String fileName) {
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
    public static String[] getResourceListing(String path) throws IOException {
        // Create a File object for the specified resource path
        File folder = new File(Objects.requireNonNull(Main.class.getResource(path)).getFile());

        // Return the list of filenames in the resource folder or directory
        return folder.list();
    }
}

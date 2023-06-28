package de.todoapp.utils;

import java.util.Map;

/**
 * Utility class for working with maps.
 *
 * @author Tobias Metzger
 * @version 1.0
 */
public class MapUtils {

    /**
     * Retrieves the key associated with the specified value in the given map.
     *
     * @param map   the map to search in
     * @param value the value to search for
     * @param <K>   the type of keys in the map
     * @param <V>   the type of values in the map
     * @return the key associated with the value, or null if the value was not found in the map
     */
    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        // Iterate over each entry in the map
        for (Map.Entry<K, V> entry : map.entrySet()) {
            // Check if the value of the entry matches the specified value
            if (entry.getValue().equals(value)) {
                // Return the key of the matching entry
                return entry.getKey();
            }
        }
        // If the value was not found, return null
        return null;
    }
}

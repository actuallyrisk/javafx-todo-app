package de.todoapp;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class accesses configuration values being defined
 * in the resources/org.example/config.properties file.
 */
public class Conf {
	private static final String BUNDLE_NAME = "org.example.config";

	private static final ResourceBundle RESOURCE_BUNDLE =
			ResourceBundle.getBundle(BUNDLE_NAME);

	private Conf() {/* Prohibiting instance creation */}

    /**
	* Accessing a key's value being defined in the project's resources/org.example/config.properties file.
	*
	* If no such key exists for a given <code>key-name</code> string the special value <code>!key-name!</code>
	* is being returned.
	*
	* @param key Key value being defined in the resources/org.example/config.properties file.
	* @return The key's corresponding value or <code>!key-name!</code> if no such definition exists.
    */
	public static String get(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}

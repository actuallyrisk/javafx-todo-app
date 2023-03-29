package de.todoapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing for existing and non-existing property keys.
 */
public class PropertyTest {
    /**
     * Testing for existing property key definitions.
     */
    @Test
    public void testExistingPropertyKeys() {
        assertEquals("Reset", Conf.get("resetButton.text"));
        assertEquals("Fields have been resetted", Conf.get("infoFieldReset"));
    }
    /**
     * Testing for an inexistent property key definition.
     */
    @Test
    public void testUndefinedPropertyKey() {
        assertEquals("!nonexistentKey!", Conf.get("nonexistentKey"));
    }
}

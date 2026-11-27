package engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChoiceTest {
    private Choice choice;

    @BeforeEach
    void setUp() {
        // Create a valid choice linking to a target story node
        choice = new Choice("Open the door", "plot1");
    }

    @Test
    void testConstructorInitializeChoice() {
        // Verify valid constructor inputs are stored properly
        assertEquals("Open the door", choice.getText());
        assertEquals("plot1", choice.getTargetNodeId());
    }

    @Test
    void testConstructorThrowTextInvalid() {
        // Null or blank choice text should be rejected with IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Choice(null, "plot1"));
        assertThrows(IllegalArgumentException.class, () -> new Choice("", "plot1"));
        assertThrows(IllegalArgumentException.class, () -> new Choice(" ", "plot1"));
    }

    @Test
    void testConstructorThrowNodeIdInvalid() {
        // Null or blank target node ID should be rejected with IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Choice("Open the door", null));
        assertThrows(IllegalArgumentException.class, () -> new Choice("Open the door", ""));
        assertThrows(IllegalArgumentException.class, () -> new Choice("Open the door", " "));
    }

    @Test
    void testGetText() {
        // Verify getText returns the choice text
        assertEquals("Open the door", choice.getText());
    }

    @Test
    void testGetTargetNodeId() {
        // Verify getTargetNodeId returns the target node ID
        assertEquals("plot1", choice.getTargetNodeId());
    }
}

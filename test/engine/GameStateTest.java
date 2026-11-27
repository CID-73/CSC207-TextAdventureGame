package engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStateTest {
    private GameState state;

    @BeforeEach
    void setUp() {
        state = new GameState("Player", "start");
    }

    @Test
    void testConstructorInitializeState() {
        // Verify player name, initial node ID, and initial history list
        assertEquals("Player", state.getPlayerName());
        assertEquals("start", state.getCurrentNodeId());
        assertEquals(List.of("start"), state.getHistory());
    }

    @Test
    void testGetPlayerName() {
        // Verify getPlayerName returns expected player name
        assertEquals("Player", state.getPlayerName());
    }

    @Test
    void testGetCurrentNodeId() {
        // Verify getCurrentNodeId returns current node ID
        assertEquals("start", state.getCurrentNodeId());
    }

    @Test
    void testGetHistory() {
        // Move through multiple nodes and verify visit order is recorded in history
        state.moveTo("plot1");
        state.moveTo("plot2");

        assertEquals(List.of("start", "plot1", "plot2"), state.getHistory());
    }

    @Test
    void testMoveToUpdatesNodeAndHistory() {
        // Moving to a target node updates currentNodeId and appends it to history
        state.moveTo("newPlot");

        assertEquals("newPlot", state.getCurrentNodeId());
        assertEquals(List.of("start", "newPlot"), state.getHistory());
    }

    @Test
    void testMultipleMovesAndRevisitingNode() {
        // Moving across multiple nodes including revisiting an earlier node-preserved order
        state.moveTo("plotA");
        state.moveTo("plotB");
        state.moveTo("start");

        assertEquals("start", state.getCurrentNodeId());
        assertEquals(List.of("start", "plotA", "plotB", "start"), state.getHistory());
    }
}

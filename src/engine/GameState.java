// GameState:
//
// GameState
// - String playerName
// - String currentNodeId
// - List<String> history
// + String getPlayerName()
// + String getCurrentId()
// + List<String> getHistory()
// + void moveTo()

package engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Game state:  represents the current state of the game, including the player's name,
 * current story node ID, and visited node history.
 */
public class GameState {
    private String playerName;
    private String currentNodeId;
    private List<String> history;

    /**
     * Starts a new game state at the story's starting scene and records it in history.
     *
     * @param playerName    the player's name
     * @param currentNodeId the ID of the starting story node
     */
    public GameState(String playerName, String currentNodeId) {
        this.playerName = playerName;
        this.currentNodeId = currentNodeId;
        this.history = new ArrayList<>();
        this.history.add(currentNodeId);
    }

    /**
     * @return the player's name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * @return the current story node ID
     */
    public String getCurrentNodeId() {
        return this.currentNodeId;
    }

    /**
     * @return the history list of story node IDs
     */
    public List<String> getHistory() {
        return this.history;
    }

    /**
     * Moves to the specified story node and appends its ID to the history.
     *
     * @param nodeId the ID of the target story node
     */
    public void moveTo(String nodeId) {
        this.currentNodeId = nodeId;
        this.history.add(nodeId);
    }
}
import java.util.ArrayList;
import java.util.List;

public class GameState {
    private String playerName;
    private String currentNodeId;
    private List<String> history;

    public GameState(String playerName, String currentNodeId){
        this.playerName = playerName;
        this.currentNodeId = currentNodeId;
        this.history = new ArrayList<>();
        this.history.add(currentNodeId);
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public String getCurrentNodeId(){
        return this.currentNodeId;
    }

    public List<String> getHistory(){
        return this.history;
    }

    void moveTo(String nodeId){
        this.currentNodeId = nodeId;
        this.history.add(nodeId);
    }

}

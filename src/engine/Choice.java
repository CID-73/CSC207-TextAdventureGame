// Choice: one option (text + target node ID) that links two scenes
//
// Choice(an edge between nodes)
// - String text
// - String targetNodeId
// + String getText()
// + String getTargetNodeId()
package engine;

public class Choice {
    private final String text;
    private final String targetNodeId;

    public Choice(String text, String targetNodeId) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Choice text must not be empty");
        }
        if (targetNodeId == null || targetNodeId.isBlank()) {
            throw new IllegalArgumentException("Choice target node must not be empty");
        }
        this.text = text;
        this.targetNodeId = targetNodeId;
    }

    public String getText() {
        return text;
    }

    public String getTargetNodeId() {
        return targetNodeId;
    }
}

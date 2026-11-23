// ChoiceView: read-only choice info for the UI (text only, no node IDs exposed)
//
// ChoiceView
// - String text
// + ChoiceView(String text)
// + String getText()
package api;

/**
 * One choice as the UI sees it: just the text to show the player.
 *
 * The engine's Choice also stores the target node ID, but the UI doesn't need it,
 * so it isn't exposed here. More fields (e.g. whether the choice is available)
 * can be added later without changing the engine's Choice.
 */
public class ChoiceView {

    // Attributes
    private final String text;

    /**
     * @param text the text shown to the player, e.g. "Open the door"
     */
    public ChoiceView(String text) {
        this.text = text;
    }

    /**
     * @return the text shown to the player.
     */
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}

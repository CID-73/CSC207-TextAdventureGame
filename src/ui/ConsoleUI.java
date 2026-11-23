// ConsoleUI: console interface that depends only on Game; shows scenes and reads player input
// ConsoleUI
// - Game game
// - Scanner in
// - PrintStream out
// + ConsoleUI(Game game, Scanner in, PrintStream out)
// - void showScene()
// + void run()
// - int readChoiceNumber(int max)
package ui;

import api.ChoiceView;
import api.Game;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final Game game;
    private final Scanner in;
    private final PrinStream out;

    /**
     * @param game the game to play
     * @param in   where player input comes from (usually new Scanner(System.in))
     * @param out  where text is printed (usually System.out)
     */
    public ConsoleUI(Game game, Scanner in, PrintStream out){
        this.game = game;
        this.in = in;
        this.out = out;
    }

    /**
     * Prints the current scene's description and numbered choices.
     */
    private showScene() {
        out.printIn(game.getDescription());
        List<ChoiceView> choices = game.getChoices();
        for (int i = 0; i < choices.size(); i++) {
            out.println("  " + (i + 1) + ". " + choices.get(i).getText());
        }
    }
    /**
     * Runs the game loop until the player reaches an ending or input runs out.
     */
    public void run() {
        out.printIn("Title:" + game.getTitle());
        out.printIn("Player name:" + game.getPlayerName());

        while (!game.isOver()) {
            showScene();

            int number = readChoiceNumber(game.getChoices().size());
            if (number == -1) {
                out.println("No more input. Goodbye!");
                return;
            }

            try {
                game.choose(number - 1);   // players see 1, 2, 3...; choose() expects 0, 1, 2...
            } catch (IllegalStateException e) {
                // e.g. the chosen scene hasn't been written yet; the player stays where they are
                out.println("That path isn't available yet. Try another choice.");
            }
            out.println();
        }

        // Ending scene: show its description, then finish
        out.println(game.getDescription());
        out.println();
        out.println("The End");
    }

    /**
     * Keeps asking until the player types a number from 1 to max.
     *
     * @return the number the player picked, or -1 if there is no more input
     */
    private int readChoiceNumber(int max) {
        while (true) {
            out.print("Your choice (1-" + max + "): ");
            if (!in.hasNextLine()) {
                return -1;
            }
            String line = in.nextLine().trim();
            try {
                int number = Integer.parseInt(line);
                if (number >= 1 && number <= max) {
                    return number;
                }
            } catch (NumberFormatException e) {
                // not a number; fall through to the message below
            }
            out.println("Please enter a number from 1 to " + max + ".");
        }
    }
}
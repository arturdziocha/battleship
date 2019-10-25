package battleship.main;

import battleship.game.ConsoleGame;
import battleship.game.Game;
import battleship.view.ConsoleView;
import battleship.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new ConsoleView();
        Game game = new ConsoleGame(view);
        game.play();
    }
}

package battleship.main.vavr;

import battleship.game.vavr.ConsoleGame;
import battleship.game.vavr.Game;
import battleship.view.vavr.ConsoleView;
import battleship.view.vavr.View;

public class Main {
	 public static void main(String[] args) {
	        View view = new ConsoleView();
	        Game game = new ConsoleGame(view);
	        game.play();
	    }
}

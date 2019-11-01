package battleship.game.vavr;

import java.util.Scanner;

import battleship.player.vavr.Player;
import battleship.view.vavr.ConsoleView;
import battleship.view.vavr.View;
import io.vavr.control.Try;

public class ConsoleGame implements Game {

	private Player firstPlayer, secondPlayer, currentPlayer;
	private Scanner reader = new Scanner(System.in);
	private View view;

	public ConsoleGame(View view) {
		this.view = view;
	}

	@Override
	public void play() {
		view.welcomeUsers();
		view.showInstructions();
		int gameMode = setupGameMode();
		setupPlayers(gameMode);
	}

	private int setupGameMode() {
		boolean gameModeChecker = false;
		int gameMode = -1;
		while (gameModeChecker == false) {
			view.showGameMode();
			Try<Integer> intTry = Try.of(() -> reader.nextInt());
			if (intTry.isSuccess()) {
				gameMode = intTry.get();
				if (gameMode == 0 || gameMode == 1 || gameMode == 2) {
					gameModeChecker = true;
				} else {
					System.out.printf(ConsoleView.error, "Please select valid Game Mode");
					gameModeChecker = false;
				}
			} else {
				System.out.printf(ConsoleView.error, "Enter valid integer value!\n");
				reader.next();
			}
		}
		return gameMode;
	}

	private void setupPlayers(int setupMode) {

		switch (setupMode) {
		case 0:
			setupHumanPlayers();
			break;
		case 1:
		case 2:
			setupHumanWithComputer(setupMode);
			break;

		}
	}

	private void setupHumanPlayers() {
		firstPlayer = setupPlayer(1);
		secondPlayer = setupPlayer(2);

	}

	private void setupHumanWithComputer(int setupMode) {
		firstPlayer = setupPlayer(1);		

	}

	private Player setupPlayer(int playerId) {
		String name = setupName(playerId);
		return null;
	}

	private String setupName(int playerId) {
		view.showNameSelect(playerId);
		return reader.next();
	}

}

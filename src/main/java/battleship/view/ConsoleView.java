package battleship.view;

import java.util.stream.IntStream;

import battleship.direction.Direction;
import battleship.fleet.Fleet;
import battleship.player.Player;
import battleship.point.Point;
import battleship.point.PointDecoder;
import battleship.point.PointImpl;
import battleship.point.PointStatus;
import battleship.ship.ShipClass;
import io.vavr.collection.Iterator;
import io.vavr.collection.List;
import io.vavr.collection.Map;

public class ConsoleView implements View {
	public static String yellow = ConsoleColor.ANSI_YELLOW + "[%s]" + ConsoleColor.ANSI_RESET + " ";
	public static String blue = ConsoleColor.ANSI_BLUE + "%s" + ConsoleColor.ANSI_RESET + "\n";
	public static String cyan = ConsoleColor.ANSI_CYAN + "%s" + ConsoleColor.ANSI_RESET + "\n";
	public static String error = ConsoleColor.ANSI_RED + "%s" + ConsoleColor.ANSI_RESET + "\n";

	@Override
	public void welcomeUsers() {
		System.out.println("WELCOME TO BATTLESHIPS\n\n");

	}

	@Override
	public void showInstructions() {
		System.out.println("INSTRUCTIONS\n" + "- If you wan't to close game before end please press Ctr+c\n"
		        + "- First you have to select game mode\n" + "- Next you will be asked for player names\n"
		        + "- Next you will be asked for ship placement mode\n"
		        + "- Before the game starts you will be asked to place your battleships\n"
		        + "- When the game starts you can select a position on the enemy board to fire on\n"
		        + "- You cannot fire outside board\n" + "- You cannot fire in the same place twice\n"
		        + "- The game is only over when either you or your enemy have no battleships are left\n");

	}

	@Override
	public void showGameMode() {
		System.out.printf(cyan, "Please select game mode");
		System.out.println("[0] - Human vs Human");
		System.out.println("[1] - Human vs Easy Computer");
		System.out.println("[2] - Human vs Gard Computer");
		System.out.printf(blue, ">>>");

	}

	@Override
	public void showNameSelect(int playerId) {
		System.out.printf(cyan, "Player " + playerId + " - give your name");
		System.out.printf(blue, ">>>");

	}

	@Override
	public void showShipPlacementModeView(String playerName) {
		System.out.printf(yellow, playerName);
		System.out.printf(cyan, "Would you like to set the ships yourself");
		System.out.println("[0] - Yes");
		System.out.println("[1] - Set randomly");
		System.out.printf(blue, ">>>");

	}

	@Override
	public void showShipsToPlace(String playerName, List<ShipClass> shipsToPlace) {
		System.out.printf(yellow, playerName);
		System.out.printf(cyan, "Please select ship to place");
		Iterator<ShipClass> shipClassIterator = shipsToPlace.iterator();
		int i = 0;
		while (shipClassIterator.hasNext()) {
			ShipClass shipClass = shipClassIterator.get();
			System.out.println("[" + i + "]" + shipClass.getName() + " - " + shipClass.getSize() + " long");
			i++;
		}
	}

	@Override
	public void showShipPositioningView(ShipClass shipClass) {
		System.out.printf(cyan, "Please set the position and direction of ship " + shipClass.getName() + " with length "
		        + shipClass.getSize() + "\n" + "Position should be between A-J and between 1-10");

		System.out.printf(cyan, "Position should be like A3");
		System.out.printf(blue, ">>>");

	}

	@Override
	public void showShipDirectionView(ShipClass shipClass) {
		System.out.printf(cyan,
		        "Please set the direction of ship " + shipClass.getName() + " with length " + shipClass.getSize());
		for (Direction direction : Direction.values()) {
			System.out.println("[" + direction.getShortName() + "]: " + direction);
		}
		System.out.printf(blue, ">>>");

	}

	@Override
	public void showFleetShips(Fleet fleet) {
		int size = PointImpl.BOARD_SIZE;
		PointStatus[][] points = new PointStatus[size][size];
		IntStream.range(0, size)
		        .forEach(row -> {
			        IntStream.range(0, size)
			                .forEach(column -> points[row][column] = PointStatus.EMPTY);
		        });
		fleet.getShips()
		        .flatMap(ship -> ship.getPoints())
		        .forEach(point -> points[point.getRow()][point.getColumn()] = PointStatus.OCCUPIED);
		fleet.getShips()
		        .flatMap(ship -> ship.getPoints())
		        .forEach(point -> points[point.getRow()][point.getColumn()] = PointStatus.OCCUPIED);

		System.out.printf("%-3s", "");
		PointDecoder.chars.take(size)
		        .forEach(letter -> System.out.printf("%-3s", " " + letter));
		System.out.println();

		IntStream.range(0, 10)
		        .forEach(row -> {
			        System.out.printf("%-3s", row + 1);
			        IntStream.range(0, 10)
			                .forEach(column -> {
				                if (points[row][column].equals(PointStatus.EMPTY)) {
					                System.out.printf("[ ]");
				                } else {
					                System.out.printf("[");
					                System.out.printf(ConsoleColor.ANSI_CYAN + "%s" + ConsoleColor.ANSI_RESET,
					                        points[row][column].getStatus());
					                System.out.printf("]");
				                }
			                });
			        System.out.println();

		        });

	}

	@Override
	public void showShots(Map<Point, PointStatus> shots, String playerName) {
		int size = PointImpl.BOARD_SIZE;
		System.out.printf(yellow, playerName);
		System.out.printf(cyan, "This is your shot board");
		PointStatus[][] shotsBoard = new PointStatus[10][10];

		IntStream.range(0, 10)
		        .forEach(row -> {
			        IntStream.range(0, 10)
			                .forEach(column -> shotsBoard[row][column] = PointStatus.EMPTY);
		        });
		shots.forEach((k, v) -> {
			shotsBoard[k.getRow()][k.getColumn()] = v;
		});
		System.out.printf("%-3s", "");
		PointDecoder.chars.take(size)
		        .forEach(letter -> System.out.printf("%-3s", " " + letter));
		System.out.println();
		IntStream.range(0, 10)
		        .forEach(row -> {
			        System.out.printf("%-3s", row + 1);
			        IntStream.range(0, 10)
			                .forEach(column -> {
				                System.out.printf("[");
				                if (shotsBoard[row][column].equals(PointStatus.EMPTY)) {
					                System.out.printf(" ");
				                } else if (shotsBoard[row][column].equals(PointStatus.HIT)) {
					                System.out.printf(ConsoleColor.ANSI_CYAN + "%s" + ConsoleColor.ANSI_RESET,
					                        shotsBoard[row][column].getStatus());
				                } else if (shotsBoard[row][column].equals(PointStatus.SUNK)) {
					                System.out.printf(ConsoleColor.ANSI_RED + "%s" + ConsoleColor.ANSI_RESET,
					                        shotsBoard[row][column].getStatus());
				                } else {
					                System.out.printf(shotsBoard[row][column].getStatus());
				                }
				                System.out.printf("]");
			                });
			        System.out.println();

		        });
	}

	@Override
	public void showShotPointView(String playerName) {
		System.out.printf(yellow, playerName);
		System.out.printf(cyan, "Please enter a point to shot like 'B1' Column must be between A-J, row between 1-10");
		System.out.printf(ConsoleView.blue, ">>>");
	}

	@Override
	public void showSpace() {
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
	}

	@Override
	public void showWinner(Player player) {
		System.out.printf(ConsoleColor.ANSI_RED + "%s" + ConsoleColor.ANSI_RESET, player.getName()
		        .toUpperCase() + "!!! You are absolutely awesome. Great job");

	}

}

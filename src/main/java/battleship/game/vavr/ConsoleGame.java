package battleship.game.vavr;

import java.util.Scanner;

import battleship.direction.vavr.Direction;
import battleship.fleet.vavr.Fleet;
import battleship.fleet.vavr.FleetImpl;
import battleship.player.vavr.ConsolePlayer;
import battleship.player.vavr.EasyComputerPlayer;
import battleship.player.vavr.HardComputerPlayer;
import battleship.player.vavr.Player;
import battleship.point.vavr.Point;
import battleship.point.vavr.PointDecoder;
import battleship.point.vavr.PointImpl;
import battleship.point.vavr.PointStatus;
import battleship.ship.vavr.Ship;
import battleship.ship.vavr.ShipClass;
import battleship.ship.vavr.ShipImpl;
import battleship.view.vavr.ConsoleColor;
import battleship.view.vavr.ConsoleView;
import battleship.view.vavr.View;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
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
		currentPlayer = firstPlayer;
		if (isReadyToStart()) {
			while (isGameFinished() == false) {
				receiveShot();
			}
		}
		Player winner = getWinner();
		System.out.printf(ConsoleColor.ANSI_RED + "%s" + ConsoleColor.ANSI_RESET, winner.getName()
		        .toUpperCase() + "!!! You are absolutely awesome. Great job");
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
		Fleet secondPlayerFleet = setupRandomShips(new FleetImpl.Builder().build());
		secondPlayer = setupMode == 1 ? new EasyComputerPlayer.Builder(secondPlayerFleet).build()
		        : new HardComputerPlayer.Builder(secondPlayerFleet).build();
	}

	private Player setupPlayer(int playerId) {
		String playerName = setupName(playerId);
		Fleet fleet = setupFleet(playerName);
		return new ConsolePlayer.Builder(playerName, fleet).build();
	}

	private String setupName(int playerId) {
		view.showNameSelect(playerId);
		return reader.next();
	}

	private Fleet setupFleet(String playerName) {
		Fleet fleet = new FleetImpl.Builder().build();
		int placementMode = setupPlacementMode(playerName);

		if (placementMode == 1) {
			fleet = setupRandomShips(fleet);
		} else if (placementMode == 0) {
			fleet = setupConsoleFleet(fleet, playerName);
		}
		return fleet;
	}

	private int setupPlacementMode(String playerName) {
		boolean placementModeChecker = false;
		int placementMode = -1;
		while (placementModeChecker == false) {
			view.showShipPlacementModeView(playerName);
			Try<Integer> intTry = Try.of(() -> reader.nextInt());
			if (intTry.isSuccess()) {
				placementMode = intTry.get();
				if (placementMode == 0 || placementMode == 1) {
					placementModeChecker = true;
				} else {
					System.out.printf(ConsoleView.error, "Please select valid Placement mode");
				}
			} else {
				System.out.printf(ConsoleView.error, "Enter valid valid integer value");
				reader.next();
			}
		}
		return placementMode;
	}

	private Fleet setupRandomShips(Fleet fleet) {
		while (!fleet.isAllShipsPlaced()) {
			fleet.placeAllShipsRandom();
		}
		return fleet;
	}

	private Fleet setupConsoleFleet(Fleet fleet, String playerName) {
		while (!fleet.isAllShipsPlaced()) {
			ShipClass shipClass = setupShipToPlace(fleet, playerName);
			Point startPoint = setupPoint(shipClass);
			Direction direction = setupDirection(shipClass);
			Either<String, Ship> ship = new ShipImpl.Builder(shipClass, startPoint, direction).build();
			if (ship.isRight()) {
				Either<String, List<Ship>> placeShip = fleet.placeShip(ship.get());
				if (placeShip.isLeft()) {
					System.out.printf(ConsoleView.error, placeShip.getLeft());
				}
			} else {
				System.out.printf(ConsoleView.error, ship.getLeft());
			}
		}
		return fleet;
	}

	private ShipClass setupShipToPlace(Fleet fleet, String playerName) {
		ShipClass shipClass = ShipClass.BARCA;
		boolean shipToPlaceChecker = false;
		while (shipToPlaceChecker == false) {
			List<ShipClass> shipsToPlace = fleet.shipsToPlace();
			view.showFleetShips(fleet);
			view.showShipsToPlace(playerName, fleet.shipsToPlace());
			Try<Integer> shipId = Try.of(() -> reader.nextInt());
			if (shipId.isSuccess()) {
				Try<ShipClass> shipTry = Try.of(() -> shipsToPlace.get(shipId.get()));
				if (shipTry.isSuccess()) {
					shipClass = shipTry.get();
					shipToPlaceChecker = true;
				} else {
					System.out.printf(ConsoleView.error, "Please select valid Ship to place");
				}
			} else {
				System.out.printf(ConsoleView.error, "Enter valid valid integer value");
				reader.next();
			}

		}
		return shipClass;
	}

	private Point setupPoint(ShipClass shipClass) {
		boolean pointChecker = false;
		Either<String, Point> point = new PointImpl.Builder().build();
		while (pointChecker == false) {
			view.showShipPositioningView(shipClass);
			Try<String> pointTry = Try.of(() -> reader.next());
			if (pointTry.isSuccess()) {
				point = new PointImpl.Builder(pointTry.get()).build();
				if (point.isRight()) {
					pointChecker = true;
				} else if (point.isLeft()) {
					System.out.printf(ConsoleView.error, point.getLeft());
				}
			} else {
				reader.next();
			}

		}
		return point.get();
	}

	private Direction setupDirection(ShipClass shipClass) {
		boolean directionChecker = false;
		Either<String, Direction> direction = Either.right(Direction.DOWN);
		while (directionChecker == false) {
			view.showShipDirectionView(shipClass);
			Try<String> dirTry = Try.of(() -> reader.next());
			if (dirTry.isSuccess()) {
				direction = Direction.getFromShortName(dirTry.get()
				        .charAt(0));
				if (direction.isRight()) {
					directionChecker = true;
				} else {
					System.out.printf(ConsoleView.error, direction.getLeft());
				}
			} else {
				reader.next();
			}
		}
		return direction.get();
	}

	public boolean isReadyToStart() {
		return firstPlayer.getFleet()
		        .isAllShipsPlaced()
		        && secondPlayer.getFleet()
		                .isAllShipsPlaced();
	}

	private boolean isGameFinished() {
		return firstPlayer.hasLost() || secondPlayer.hasLost();
	}

	private void receiveShot() {
		if (currentPlayer instanceof ConsolePlayer) {
			view.showShots(currentPlayer.getShots(), currentPlayer.getName());
		}
		Either<String, Point> pointEither = currentPlayer.prepareShot();
		if (pointEither.isRight()) {
			Point point = pointEither.get();
			if (!point.isOutsideBoard()) {
				if (!currentPlayer.isAlreadyShooted(point)) {
					Option<Ship> optShip = getOponent().getFleet()
					        .shipAt(point);
					// TODO
					optShip.peek(ship -> {
						ship.shoot();
						if (ship.isSunk()) {
							currentPlayer.setShotSunk(ship);
							System.out.printf(ConsoleView.cyan, "Yeah, you kill ship " + ship.getShipClass() + "-"
							        + ship.getSize() + " at point " + PointDecoder.pointToString(point));
						} else {
							currentPlayer.setShot(point, PointStatus.HIT);
							System.out.printf(ConsoleView.cyan, "Yeah, you shot ship " + ship.getShipClass() + "-"
							        + ship.getSize() + " at point " + PointDecoder.pointToString(point));
						}
						receiveShot();
					})
					        .onEmpty(() -> setMissAndSwitchPlayer(point));
				} else {
					System.out.printf(ConsoleView.error, "You shooted outside board");
					switchPlayer();
				}
			} else {
				System.out.printf(ConsoleView.error, "You shooted outside board");
				switchPlayer();
			}

		} else {
			System.out.printf(ConsoleView.error, pointEither.getLeft());
			switchPlayer();
		}
	}

	private void switchPlayer() {
		currentPlayer = currentPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
	}

	private Player getOponent() {
		return currentPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
	}

	private void setMissAndSwitchPlayer(Point point) {
		currentPlayer.setShot(point, PointStatus.MISS);
		switchPlayer();
	}

	private Player getWinner() {
		return firstPlayer.hasLost() ? secondPlayer : firstPlayer;
	}

}

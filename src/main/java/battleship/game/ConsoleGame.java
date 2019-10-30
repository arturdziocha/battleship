package battleship.game;

import java.util.Optional;
import java.util.Scanner;

import battleship.direction.Direction;
import battleship.exception.MalformattedException;
import battleship.exception.ShipPlacementException;
import battleship.fleet.Fleet;
import battleship.fleet.FleetImpl;
import battleship.player.ConsolePlayer;
import battleship.player.EasyComputerPlayer;
import battleship.player.HardComputerPlayer;
import battleship.player.Player;
import battleship.point.Point;
import battleship.point.PointDecoder;
import battleship.point.PointImpl;
import battleship.point.PointStatus;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import battleship.ship.ShipImpl;
import battleship.view.ConsoleView;
import battleship.view.View;

public class ConsoleGame implements Game {
	private Player firstPlayer, secondPlayer, currentPlayer;
	private Scanner reader = new Scanner(System.in);
	private final View view;

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
	}

	private int setupGameMode() {
		boolean gameModeChecker = false;
		int gameMode = -1;
		while (gameModeChecker == false) {
			view.showGameMode();
			if (reader.hasNextInt()) {
				gameMode = reader.nextInt();
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
		String name = setupName(playerId);
		Fleet fleet = setupFleet(name);

		return new ConsolePlayer.Builder(name, fleet).build();
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
			if (reader.hasNextInt()) {
				placementMode = reader.nextInt();
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
			try {
				fleet.placeShipsRandom();
			} catch (MalformattedException e) {
				System.out.printf(ConsoleView.error, e.getMessage());
			}
		}
		return fleet;
	}

	private Fleet setupConsoleFleet(Fleet fleet, String playerName) {
		while (!fleet.isAllShipsPlaced()) {
			ShipClass shipClass = setupShipToPlace(fleet, playerName);
			Point startPoint = setupPoint(shipClass);
			Direction direction = setupDirection(shipClass);
			try {
				Ship ship = new ShipImpl.Builder(shipClass).points(startPoint, direction)
				        .build();
				fleet.placeShip(ship);
			} catch (MalformattedException e) {
				System.out.printf(ConsoleView.error, e.getMessage());
			} catch (ShipPlacementException e) {
				System.out.printf(ConsoleView.error, e.getMessage());
			}
		}
		return fleet;
	}

	private ShipClass setupShipToPlace(Fleet fleet, String playerName) {

		ShipClass shipClass = ShipClass.BARCA;
		boolean shipToPlaceChecker = false;
		while (shipToPlaceChecker == false) {
			view.showFleetShips(fleet);
			view.showShipsToPlace(playerName, fleet.shipsToPlace());
			if (reader.hasNextInt()) {
				int shipIdToPlace = reader.nextInt();
				if (shipIdToPlace >= 0 && shipIdToPlace < fleet.shipsToPlace()
				        .size()) {
					shipClass = fleet.shipsToPlace()
					        .get(shipIdToPlace);
					shipToPlaceChecker = true;
					return shipClass;
				} else {
					System.out.printf(ConsoleView.error, "Please select valid Placement mode");
				}
			} else {
				System.out.printf(ConsoleView.error, "Enter valid valid integer value");
				reader.next();
			}
		}
		return null;

	}

	private Point setupPoint(ShipClass shipClass) {
		boolean pointChecker = false;
		while (pointChecker == false) {
			view.showShipPositioningView(shipClass);
			if (reader.hasNext()) {
				try {
					Point point = new PointImpl.Builder(reader.next()).build();
					pointChecker = true;
					return point;
				} catch (MalformattedException e) {
					System.out.printf(ConsoleView.error, e.getMessage());
					pointChecker = false;
				}
			} else {
				reader.next();
			}
		}
		return null;
	}

	private Direction setupDirection(ShipClass shipClass) {
		boolean directionChecker = false;
		while (directionChecker == false) {
			view.showShipDirectionView(shipClass);
			if (reader.hasNext()) {
				try {
					Direction direction = Direction.getFromShortName(reader.next()
					        .charAt(0));
					directionChecker = true;
					return direction;
				} catch (MalformattedException e) {
					System.out.printf(ConsoleView.error, e.getMessage());
					directionChecker = false;
				}
			} else {
				reader.next();
			}
		}
		return Direction.DOWN;
	}

	public boolean isReadyToStart() {
		return firstPlayer.getFleet()
		        .isAllShipsPlaced()
		        && secondPlayer.getFleet()
		                .isAllShipsPlaced();
	}

	private void receiveShot() {
		try {
			if (currentPlayer instanceof ConsolePlayer) {
				view.showShots(currentPlayer.getShots(), currentPlayer.getName());
				System.out.println(firstPlayer.getShots());
				firstPlayer.getShots()
				        .forEach((k, v) -> {
					        System.out.println("[" + PointDecoder.pointToString(k) + "]-" + v);
				        });
			}
			Point shotPoint = currentPlayer.prepareShot();
			if (shotPoint.isInsideBoard()) {
				System.out.println(
				        "Player " + currentPlayer.getName() + " your shot is " + PointDecoder.pointToString(shotPoint));
				if (!currentPlayer.isAlreadyShooted(shotPoint)) {
					Player oponent = getOponent();
					Optional<Ship> optShip = oponent.getFleet()
					        .shipAt(shotPoint);
					optShip.ifPresent(ship -> {
						ship.shoot();
						if (ship.isSunk()) {
							currentPlayer.setShotSunk(ship);
							System.out.printf(ConsoleView.cyan, "Yeah, you kill ship " + ship.getShipClass());
							receiveShot();
						} else {
							currentPlayer.setShot(shotPoint, PointStatus.HIT);
							System.out.printf(ConsoleView.cyan, "Yeah, you shot ship " + ship.getShipClass());
							receiveShot();
						}

					});
					if (!optShip.isPresent()) {
						currentPlayer.setShot(shotPoint, PointStatus.MISS);
						switchPlayer();
					}

				} else {
					System.out.printf(ConsoleView.error, "You already shot this point. Try one more time");
					receiveShot();
				}
			} else {
				System.out.printf(ConsoleView.error, "Please select point inside board!");
				receiveShot();
			}
		} catch (MalformattedException e) {
			System.out.printf(ConsoleView.error, e.getMessage());
		}
	}

	private boolean isGameFinished() {
		return firstPlayer.hasLost() || secondPlayer.hasLost();
	}

	private Player getOponent() {
		return currentPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
	}

	private void switchPlayer() {
		currentPlayer = currentPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
	}

}

package battleship.game;

import java.util.List;
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
import battleship.point.PointImpl;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import battleship.ship.ShipImpl;
import battleship.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleGame implements Game {
	final Logger logger = LoggerFactory.getLogger(ConsoleGame.class);
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
		view.showGameMode();
		int mode = reader.nextInt();
		setupPlayers(mode);
		System.out.println(mode);
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
		firstPlayer = setupPlayer();
		secondPlayer = setupPlayer();

	}

	private void setupHumanWithComputer(int setupMode) {
		firstPlayer = setupPlayer();
		Fleet secondPlayerFleet = new FleetImpl.Builder().build();
		try {
			secondPlayerFleet.placeShipsRandom();
			secondPlayer = setupMode == 1 ? new EasyComputerPlayer.Builder(secondPlayerFleet).build()
			        : new HardComputerPlayer.Builder(secondPlayerFleet).build();
		} catch (MalformattedException e) {
			System.out.println(e.getMessage());
		}

	}

	private Player setupPlayer() {
		String name = setupName();
		Fleet fleet = setupFleet();

		return new ConsolePlayer.Builder(name, fleet).build();
	}

	private String setupName() {
		view.showNameSelect();
		return reader.next();
	}

	private Fleet setupFleet() {
		Fleet fleet = new FleetImpl.Builder().build();

		int placementMode;
		do {
			view.showShipPlacementMode();
			placementMode = reader.nextInt();
			logger.error("placement mode = {}", placementMode);
			if (placementMode == 1) {
				try {
					fleet.placeShipsRandom();
				} catch (MalformattedException e) {
					System.out.println(e.getMessage());
				}

			} else if (placementMode == 0) {
				while (!fleet.isAllShipsPlaced()) {
					boolean shipIdChecker = false;

					do {
						List<ShipClass> shipsToPlace = fleet.shipsToPlace();
						logger.error("{}", fleet.getShips());

						view.showFleetShips(fleet);
						view.showShipsToPlace(shipsToPlace);
						int shipIdToPlace = reader.nextInt();
						try {
							ShipClass shipClass = shipsToPlace.get(shipIdToPlace);
							shipIdChecker = true;

							boolean pointChecker = false;
							do {								
								view.showShipPositioningView(shipClass);
								String pointString = reader.next();
								String directionString = reader.next();

								try {
									Point startPoint = new PointImpl.Builder(pointString).build();
									Direction direction = Direction.getFromShortName(directionString.charAt(0));
									pointChecker = true;
									Ship ship = new ShipImpl.Builder(shipClass).points(startPoint, direction)
									        .build();
									fleet.placeShip(ship);
								} catch (MalformattedException e) {
									System.out.println(e.getMessage());
									pointChecker = false;
								} catch (ShipPlacementException e) {
									System.out.println(e.getMessage());
									pointChecker = false;
								}
							} while (pointChecker == false);

						} catch (IndexOutOfBoundsException e) {
							System.out.println(e.getMessage());
							shipIdChecker = false;
						}
					} while (shipIdChecker == true);

				}
			}

		} while (placementMode != 0 || placementMode != 1);

		return fleet;
	}

}

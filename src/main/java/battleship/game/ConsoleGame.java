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
		view.showGameMode();
		int mode = reader.nextInt();
		setupPlayers(mode);
		System.out.println(mode);
	}

	private void setupPlayers(int mode) {

		switch (mode) {
		case 0:
			setupHumanPlayers();
			break;
		case 1:
		case 2:
			setupHumanWithComputer(mode);
			break;

		}
	}

	private void setupHumanPlayers() {

	}

	private void setupHumanWithComputer(int mode) {
		String firstPlayerName = setupName();
		Fleet firstPlayerFleet = new FleetImpl.Builder().build();
		view.showShipPlacementMode();
		int placementMode = reader.nextInt();
		if (placementMode == 0) {
			firstPlayerFleet.placeShipsRandom();
		} else if (placementMode == 1) {
			while (!firstPlayerFleet.isAllShipsPlaced()) {
				List<ShipClass> shipsToPlace = firstPlayerFleet.shipsToPlace();
				view.showShipsToPlace(shipsToPlace);
				int shipToPlaceId = reader.nextInt();
				view.showShipPositioningView(shipsToPlace.get(shipToPlaceId));
				String position = reader.next();
				String directionStr = reader.next();
				Point startPoint = new PointImpl.Builder(position).build();
				Direction direction = Direction.getFromShortName(directionStr);
				Ship ship = new ShipImpl.Builder(shipsToPlace.get(shipToPlaceId)).points(startPoint, direction)
				        .build();
			}
		}
		Fleet secondPlayerFleet = new FleetImpl.Builder().build();
		secondPlayerFleet.placeShipsRandom();
		secondPlayer = mode == 1 ? new EasyComputerPlayer.Builder(secondPlayerFleet).build()
		        : new HardComputerPlayer.Builder(secondPlayerFleet).build();
	}

	private Player setupPlayer(int playerNumber) {
		String name = setupName();
		Fleet fleet = new FleetImpl.Builder().build();

		view.showShipPlacementMode();
		int placementMode = reader.nextInt();
		while (placementMode != 0 || placementMode != 1) {
			view.showShipPlacementMode();
			placementMode = reader.nextInt();
		}
		if (placementMode == 1) {
			try {
				fleet.placeShipsRandom();
				while (!fleet.isAllShipsPlaced()) {
					fleet.placeShipsRandom();
				}
			} catch (MalformattedException e) {
				System.out.println(e.getMessage());
			}

		} else if (placementMode == 0) {
			while (!fleet.isAllShipsPlaced()) {
				List<ShipClass> shipsToPlace = fleet.shipsToPlace();

				view.showShipsToPlace(shipsToPlace);
				int shipIdToPlace = reader.nextInt();
				while (shipIdToPlace >= shipsToPlace.size()) {
					System.out.println("Something went wrong. Try again");
					view.showShipsToPlace(shipsToPlace);
					shipIdToPlace = reader.nextInt();
				}
				ShipClass shipClass = shipsToPlace.get(shipIdToPlace);
				
				view.showShipPositioningView(shipClass);
				String pointString = reader.next();
				String directionString = reader.next();
			}
		}
		return new ConsolePlayer.Builder(name, fleet).build();
	}

	private String setupName() {
		view.showNameSelect();
		return reader.next();
	}

}

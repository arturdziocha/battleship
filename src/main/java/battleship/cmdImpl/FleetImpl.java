package battleship.cmdImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import battleship.Fleet;
import battleship.Point;
import battleship.enums.Direction;
import battleship.enums.ShipClass;
import battleship.ship.Ship;

public class FleetImpl implements Fleet {
	private List<Ship> ships;
	private Random random = new Random();

	public FleetImpl() {
		ships = new ArrayList<>();
	}

	@Override
	public boolean placeShip(Ship ship) {
		if (ship.getSize() > SIZE || isInsideBoard(ship) || isToClose(ship) || isAlreadyPlaced(ship)) {
			return false;
		} else {			
			ships.add(ship);
			return true;
		}
	}

	@Override
	public void placeShipsRandom() {
		while (!isAllShipsPlaced()) {
			ShipClass[] shipClasses = ShipClass.values();
			for (ShipClass shipClass : shipClasses) {
				Direction direction = Direction.values()[random.nextInt(4)];
				Point point = lotteryRowAndColumn(shipClass, direction);
				Ship ship = new ShipImpl(shipClass, direction, point);
				placeShip(ship);
			}
		}
	}

	@Override
	public List<Ship> getShips() {
		return ships;
	}

	@Override
	public boolean isAllShipsPlaced() {
		List<ShipClass> shipClasses = Arrays.asList(ShipClass.values())
		        .stream()
		        .sorted()
		        .collect(Collectors.toList());
		List<ShipClass> fleetShipClasses = ships.stream()
		        .map(Ship::getShipClass)
		        .sorted()
		        .collect(Collectors.toList());
		return shipClasses.equals(fleetShipClasses);
	}

	@Override
	public Optional<Ship> shipAt(Point point) {
		return ships.stream()
		        .filter(s -> s.getPointAt(point)
		                .isPresent())
		        .findAny();

	}

	private boolean isInsideBoard(Ship ship) {
		return ship.getMostBottomPosition() >= SIZE || ship.getMostTopPosition() < 0
		        || ship.getMostRightPosition() >= SIZE || ship.getMostLeftPosition() < 0;
	}

	private boolean isToClose(Ship ship) {
		for (Ship s : ships) {
			if (s.toCloseTo(ship)) {
				return true;
			}
		}
		return false;
	}

	private boolean isAlreadyPlaced(Ship ship) {
		return ships.stream()
		        .anyMatch(s -> s.getShipClass()
		                .equals(ship.getShipClass()));
	}

	private Point lotteryRowAndColumn(ShipClass shipClass, Direction direction) {
		int row = 0;
		int column = 0;
		switch (direction) {
		case Top:
			row = random.nextInt(10) + shipClass.getSize();
			column = random.nextInt(10);
			break;
		case Bottom:
			row = random.nextInt(10 - shipClass.getSize());
			column = random.nextInt(10);
			break;
		case Left:
			row = random.nextInt(10);
			column = random.nextInt(10) + shipClass.getSize();
		case Right:
			row = random.nextInt(10);
			column = random.nextInt(10) - shipClass.getSize();
		}
		return new PointImpl(row, column);
	}

}

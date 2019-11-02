package battleship.fleet;

import battleship.point.Point;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import battleship.ship.ShipImpl;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.collection.TreeSet;
import io.vavr.control.Either;
import io.vavr.control.Option;

public class FleetImpl implements Fleet {
	public static class Builder {
		private List<Ship> ships;

		public Fleet build() {
			this.ships = List.empty();
			return new FleetImpl(this);
		}
	}

	private List<Ship> ships;

	private FleetImpl(Builder builder) {
		ships = builder.ships;
	}

	@Override
	public Either<String, List<Ship>> placeShip(Ship ship) {
		if (ship == null) {
			return Either.left("Ship cannot be null");
		}
		if (isAlreadyPlaced(ship)) {
			return Either.left("Ship is aleady placed!.");
		}
		if (isOutsideBoard(ship)) {
			return Either.left("Ship is outside board");
		}

		if (isToClose(ship)) {
			return Either.left("Ship is to close another placed ship");
		}
		ships = ships.append(ship);
		return Either.right(ships);

	}

	@Override
	public void placeAllShipsRandom() {
		while (!isAllShipsPlaced()) {
			for (ShipClass shipClass : ShipClass.values()) {
				Either<String, Ship> ship = new ShipImpl.Builder(shipClass).build();
				while (placeShip(ship.get()).isLeft()) {
					ship = new ShipImpl.Builder(shipClass).build();
				}
			}
		}
	}

	@Override
	public List<Ship> getShips() {
		return ships;
	}

	@Override
	public List<ShipClass> shipsToPlace() {
		List<ShipClass> shipClasses = shipClasses();
		Set<ShipClass> alreadySet = shipClassesAlreadyInFleet();
		return shipClasses.removeAll(alreadySet);
	}

	public boolean isAllShipsPlaced() {
		Set<ShipClass> allShipClassesSet = TreeSet.of(ShipClass.values());
		Set<ShipClass> placedSet = shipClassesAlreadyInFleet();
		return allShipClassesSet.equals(placedSet);
	}

	@Override
	public Option<Ship> shipAt(Point point) {
		return ships.find(ship -> ship.isAt(point)
		        .isDefined());
	}

	@Override
	public boolean isAllShipsSunk() {
		Option<Ship> optShip = ships.find(ship -> !ship.isSunk());
		return optShip.isDefined() ? false : true;
	}

	private boolean isAlreadyPlaced(Ship ship) {
		if (ships.isEmpty()) {
			return false;
		}
		Option<Ship> optShip = ships.find(s -> s.getShipClass()
		        .equals(ship.getShipClass()));
		return optShip.isDefined() ? true : false;
	}

	private boolean isOutsideBoard(Ship ship) {
		Option<Point> optPointOption = ship.getPoints()
		        .find(Point::isOutsideBoard);
		return optPointOption.isDefined() ? true : false;
	}

	private boolean isToClose(Ship ship) {
		for (Ship s : ships) {
			if (s.toCloseTo(ship)) {
				return true;
			}
		}
		return false;
	}

	private List<ShipClass> shipClasses() {
		return List.of(ShipClass.values());
	}

	private Set<ShipClass> shipClassesAlreadyInFleet() {
		return ships.map(Ship::getShipClass)
		        .collect(TreeSet.collector());
	}
}

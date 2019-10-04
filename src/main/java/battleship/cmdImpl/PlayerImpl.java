package battleship.cmdImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import battleship.Fleet;
import battleship.Player;
import battleship.enums.PointStatus;
import battleship.point.Point;
import battleship.ship.Ship;

public abstract class PlayerImpl implements Player {

	private Fleet fleet;
	private Map<Point, PointStatus> shots;

	public PlayerImpl() {
		this.fleet = new FleetImpl();
		this.shots = new HashMap<>();
	}

	@Override
	public abstract String getName();

	@Override
	public boolean placeShip(Ship ship) {
		return fleet.placeShip(ship);
	}

	@Override
	public void placeShipsRandom() {
		fleet.placeShipsRandom();

	}

	@Override
	public Fleet getFleet() {
		return fleet;
	}

	@Override
	public Optional<Ship> shipAt(Point point) {
		return fleet.shipAt(point);
	}

	@Override
	public void setShot(Point point, Ship ship) {
		if(ship.isSunk()) {
			ship.getPoints();
		}
	}

}

package battleship;

import java.util.Optional;

import battleship.v0.point.Point;
import battleship.v0.ship.Ship;

public interface Player {
	abstract String getName();
	boolean placeShip(Ship ship);
	void placeShipsRandom();
	Fleet getFleet();
	Optional<Ship> shipAt(Point point);
	void setShot(Point point, Ship ship);
	
}

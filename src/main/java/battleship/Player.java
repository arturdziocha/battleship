package battleship;

import java.util.Optional;

import battleship.point.Point;
import battleship.ship.Ship;

public interface Player {
	abstract String getName();
	boolean placeShip(Ship ship);
	void placeShipsRandom();
	Fleet getFleet();
	Optional<Ship> shipAt(Point point);
	void setShot(Point point, Ship ship);
	
}

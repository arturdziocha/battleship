package battleship.interfaces;

import java.util.List;

import battleship.Direction;
import battleship.enums.ShipClass;

public interface Ship {
	ShipClass getShipClass();
	abstract int getSize();
	Direction getDirection();
	Point getPoint();
	List<Point> getPoints();
	boolean isSunk();
	boolean toCloseTo(Ship other);
	void shoot(Point point);
	
}

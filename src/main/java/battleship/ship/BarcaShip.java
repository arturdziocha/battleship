package battleship.ship;

import java.util.List;
import java.util.Optional;

import battleship.Point;
import battleship.enums.Direction;
import battleship.enums.ShipClass;

public class BarcaShip implements Ship{

	@Override
	public ShipClass getShipClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Point> getPointAt(Point point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Point> getPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSunk() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean toCloseTo(Ship other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shoot(Point point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMostTopPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostBottomPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostLeftPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostRightPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

}

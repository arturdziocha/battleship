package battleship;

import java.util.ArrayList;
import java.util.List;

import battleship.enums.ShipClass;
import battleship.interfaces.Point;
import battleship.interfaces.Ship;

public class ShipImpl implements Ship {
	private ShipClass shipClass;
	private int size;
	private Direction direction;
	private Point point;
	private List<Point> points;

	public ShipImpl(ShipClass shipClass, Direction direction, Point point) {
		this.shipClass = shipClass;
		this.size = shipClass.getSize();
		this.direction = direction;
		this.point = point;
		this.points = new ArrayList<>();

		switch (direction) {
		case North:
			for (int i = shipClass.getSize(); i > 0; i--) {
				this.points.add(new PointImpl(point.getRow() + 1 - i, point.getColumn()));
			}
		}
	}

	@Override
	public ShipClass getShipClass() {
		return shipClass;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public Point getPoint() {
		return point;
	}

	@Override
	public List<Point> getPoints() {
		return points;
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

}

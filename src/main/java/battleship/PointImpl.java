package battleship;

import battleship.enums.PointStatus;
import battleship.interfaces.Point;

public class PointImpl implements Point {
	private int row;
	private int column;
	private PointStatus status;

	public PointImpl(int row, int column) {
		this.row = row;
		this.column = column;
		this.status = PointStatus.Empty;

	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public boolean isEmpty() {
		return status == PointStatus.Empty;
	}

	public boolean isHit() {
		return status == PointStatus.Hit;
	}

	public boolean isOccupied() {
		return status == PointStatus.Occupied;
	}

	public void occupy() {
		this.status = PointStatus.Occupied;
	}

	public void hit() {
		this.status = PointStatus.Hit;
	}

	@Override
	public boolean isNeighbor(Point other) {
		return (Math.abs(getRow() - other.getRow()) <= 1 && getColumn() - other.getColumn() <= 1);
	}

	@Override
	public boolean equals(Point other) {
		return (getRow() == other.getRow() && getColumn() == other.getColumn()) ? true : false;
	}

}

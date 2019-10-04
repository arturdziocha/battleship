package battleship.v1.point;

public class PointImpl implements Point {
	private int row;
	private int column;
	private PointStatus status;

	public PointImpl(int row, int column) {
		this.row = row;
		this.column = column;
		this.status = PointStatus.EMPTY;

	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public boolean isEmpty() {
		return status == PointStatus.EMPTY;
	}

	public boolean isHit() {
		return status == PointStatus.HIT;
	}

	/**
	 * public boolean isOccupied() { return status == PointStatus.Occupied; }
	 * 
	 * public void occupy() { this.status = PointStatus.Occupied; }
	 */

	public void hit() {
		this.status = PointStatus.HIT;
	}

	@Override
	public boolean isNeighbor(Point other) {
		return (Math.abs(getRow() - other.getRow()) <= 1) && (Math.abs(getColumn() - other.getColumn()) <= 1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointImpl other = (PointImpl) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

}

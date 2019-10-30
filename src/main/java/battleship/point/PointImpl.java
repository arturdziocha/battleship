package battleship.point;

import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import battleship.exception.MalformattedException;

public final class PointImpl implements Point, Comparable<Point> {
	private static final Comparator<Point> COMPARATOR = Comparator.comparingInt(Point::getRow)
	        .thenComparing(Point::getColumn);
	private final int row;
	private final int column;
	private int boardSize = 10;

	public static class Builder {
		private int row;
		private int column;

		public Builder(int row, int column) {
			this.row = row;
			this.column = column;
		}

		/**
		 * Build point with random row and column
		 */
		public Builder() {
			Random random = new Random();
			row = random.nextInt(10);
			column = random.nextInt(10);
		}

		/**
		 * Build point from pointString
		 *
		 * @param pointString
		 * @throws MalformattedException
		 */
		public Builder(String pointString) throws MalformattedException {
			row = PointDecoder.getRow(pointString);
			column = PointDecoder.getColumn(pointString);
		}

		public PointImpl build() {
			return new PointImpl(this);
		}
	}

	private PointImpl(Builder builder) {
		this.row = builder.row;
		this.column = builder.column;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	@Override
	public boolean isNeighbor(Point other) {
		return (Math.abs(getRow() - other.getRow()) <= 1) && (Math.abs(getColumn() - other.getColumn()) <= 1);
	}

	@Override
	public Set<Point> calculateNeighbors() {
		Set<Point> toReturn = new TreeSet<>();
		for (int i = -1; i <= 1; i++) {
			toReturn.add(new PointImpl.Builder(row - 1, column + i).build());
			toReturn.add(new PointImpl.Builder(row + 1, column + i).build());
		}
		toReturn.add(new PointImpl.Builder(row, column - 1).build());
		toReturn.add(new PointImpl.Builder(row, column + 1).build());

		return toReturn;
	}

	@Override
	public boolean isInsideBoard() {
		return row >= 0 && row < boardSize && column >= 0 && column < boardSize;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (!(o instanceof PointImpl)) {
			return false;
		}

		return (row == ((PointImpl) o).row) && (column == ((PointImpl) o).column);
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
	public String toString() {
		return "[row=" + row + ", column=" + column + "]";
	}

	@Override
	public int compareTo(Point other) {
		return COMPARATOR.compare(this, other);
	}

}

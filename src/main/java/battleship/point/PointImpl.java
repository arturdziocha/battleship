package battleship.point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import battleship.exception.MalformattedException;

public final class PointImpl implements Point, Comparable<Point> {
    private final int row;
    private final int column;

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
    public List<Point> calculateNeighbors() {
        List<Point> toReturn = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            toReturn.add(new PointImpl.Builder(row - 1, column + i).build());
            toReturn.add(new PointImpl.Builder(row + 1, column + i).build());
        }
        toReturn.add(new PointImpl.Builder(row, column - 1).build());
        toReturn.add(new PointImpl.Builder(row, column + 1).build());

        return toReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PointImpl point = (PointImpl) o;
        return row == point.row && column == point.column;
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
        return new Double(distance(this)).compareTo(distance(other));
    }

    private double distance(Point p) {
        return (double) Math.sqrt(Math.pow(p.getRow(), 2) + Math.pow(p.getColumn(), 2));
    }

}

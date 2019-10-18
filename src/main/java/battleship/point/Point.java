package battleship.point;

import java.util.Random;

import battleship.exception.MalformattedException;

public class Point {
    private final int row;
    private final int column;

    public static class Builder {
        private int row;
        private int column;

        public Builder(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public Builder(String pointString) throws MalformattedException {
            row = PointDecoder.getRow(pointString);
            column = PointDecoder.getColumn(pointString);
        }

        public Point build() {
            return new Point(this);
        }
    }

    private Point(Builder builder) {
        this.row = builder.row;
        this.column = builder.column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isNeighbor(Point other) {
        return (Math.abs(getRow() - other.getRow()) <= 1) && (Math.abs(getColumn() - other.getColumn()) <= 1);
    }

    public static Point getRandomPoint() {
        Random random = new Random();
        int row = random.nextInt(10);
        int col = random.nextInt(10);
        return new Point.Builder(row, col).build();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point point = (Point) o;
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
    
}

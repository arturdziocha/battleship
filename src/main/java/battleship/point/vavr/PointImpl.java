package battleship.point.vavr;

import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import io.vavr.control.Either;

public final class PointImpl implements Point, Comparable<Point> {
    private static final Comparator<Point> COMPARATOR = Comparator.comparingInt(Point::getRow)
            .thenComparing(Point::getColumn);
    public static final int BOARD_SIZE = 10;

    public static class Builder {
        private final Either<String, Integer> row;
        private final Either<String, Integer> column;

        /**
         * Build point with random row and column
         */
        public Builder() {
            Random random = new Random();
            row = Either.right(random.nextInt(BOARD_SIZE));
            column = Either.right(random.nextInt(BOARD_SIZE));
        }

        /**
         * Build point from row and column
         * 
         * @param row
         * @param column
         */
        public Builder(int row, int column) {
            this.row = Either.right(row);
            this.column = Either.right(column);
        }

        /**
         * Build point from pointString
         * 
         * @param pointString
         */
        public Builder(String pointString) {
            row = PointDecoder.getRow(pointString);
            column = PointDecoder.getColumn(pointString);
        }

        public Either<String, Point> build() {
            if (row.isLeft()) {
                return Either.left(row.getLeft());
            }
            if (column.isLeft()) {
                return Either.left(column.getLeft());
            }
            return Either.right(new PointImpl(this));
        }
    }

    private int row;
    private int column;

    private PointImpl(Builder builder) {
        this.row = builder.row.get();
        this.column = builder.column.get();
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
        Either<String, Point> point;
        for (int i = -1; i <= 1; i++) {
            point = new PointImpl.Builder(row - 1, column + i).build();
            if (point.isRight()) {
                if (point.get()
                        .isInsideBoard()) {
                    toReturn.add(point.get());
                }
            }
            point = new PointImpl.Builder(row + 1, column + i).build();
            if (point.isRight()) {
                if (point.get()
                        .isInsideBoard()) {
                    toReturn.add(point.get());
                }
            }
        }
        point = new PointImpl.Builder(row, column - 1).build();
        if (point.isRight()) {
            if (point.get()
                    .isInsideBoard()) {
                toReturn.add(point.get());
            }
        }
        point = new PointImpl.Builder(row, column + 1).build();
        if (point.isRight()) {
            if (point.get()
                    .isInsideBoard()) {
                toReturn.add(point.get());
            }
        }
        return toReturn;
    }

    @Override
    public boolean isInsideBoard() {
        return row >= 0 && row < BOARD_SIZE && column >= 0 && column < BOARD_SIZE;
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

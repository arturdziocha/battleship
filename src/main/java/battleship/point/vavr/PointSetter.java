package battleship.point.vavr;

import java.util.ArrayList;
import java.util.List;

import battleship.direction.vavr.Direction;
import io.vavr.control.Either;

public final class PointSetter {
    public static class Builder {
        private final int size;
        private Either<String, Point> startPoint;
        private Either<String, Direction> direction;

        public Builder(int size) {
            this.size = size;
        }

        public Builder(int size, Point startPoint, Direction direction) {
            this.size = size;
            this.startPoint = Either.right(startPoint);
            this.direction = Either.right(direction);
        }

        public Builder startPoint() {
            this.startPoint = new PointImpl.Builder().build();
            return this;
        }

        public Builder direction() {
            this.direction = Either.right(Direction.getRandomDirection());
            return this;
        }

        public Either<String, PointSetter> build() {
            if (startPoint == null || direction == null) {
                return Either.left("Direction or point cannot be empty");
            }
            if (startPoint.isLeft() && startPoint.isLeft()) {
                return Either.left("Direction and point cannot be empty");
            }
            if (direction.isLeft()) {
                return Either.left("\"Direction cannot be empty\"");
            }
            if (startPoint.isLeft()) {
                return Either.left("Point cannot be empty");
            }
            return Either.right(new PointSetter(this));
        }
    }

    private final int size;
    private final Point startPoint;
    private final Direction direction;
    private ArrayList<Point> points;

    private PointSetter(Builder builder) {
        this.size = builder.size;
        this.startPoint = builder.startPoint.get();
        this.direction = builder.direction.get();
        this.points = new ArrayList<>();
    }

    public List<Point> getPoints() {
        switch (direction) {
            case UP:
                fillTop();
                break;
            case DOWN:
                fillBottom();
                break;
            case RIGHT:
                fillRight();
                break;
            case LEFT:
                fillLeft();
                break;
        }
        return points;
    }

    private void fillTop() {
        for (int i = 0; i < size; i++) {
            Either<String, Point> point = new PointImpl.Builder(startPoint.getRow() - i, startPoint.getColumn())
                    .build();
            if (point.isRight()) {
                this.points.add(point.get());
            }
        }
    }

    private void fillBottom() {
        for (int i = 0; i < size; i++) {
            Either<String, Point> point = new PointImpl.Builder(startPoint.getRow() + i, startPoint.getColumn())
                    .build();
            if (point.isRight()) {
                this.points.add(point.get());
            }
        }
    }

    private void fillRight() {
        for (int i = 0; i < size; i++) {
            Either<String, Point> point = new PointImpl.Builder(startPoint.getRow(), startPoint.getColumn() + i)
                    .build();
            if (point.isRight()) {
                this.points.add(point.get());
            }
        }
    }

    private void fillLeft() {
        for (int i = 0; i < size; i++) {
            Either<String, Point> point = new PointImpl.Builder(startPoint.getRow(), startPoint.getColumn() - i)
                    .build();
            if (point.isRight()) {
                this.points.add(point.get());
            }
        }
    }
}

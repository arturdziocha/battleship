package battleship.point;

import java.util.ArrayList;
import java.util.List;

import battleship.direction.Direction;
import battleship.exception.MalformattedException;

public class PointsSetter {
    private final int size;
    private final Point startPoint;
    private final Direction direction;
    private List<Point> points;

    public static class Builder {
        private int size;
        private Point startPoint;
        private Direction direction;

        public Builder(int size) {
            this.size = size;
        }

        public Builder startPoint() {
            this.startPoint = Point.getRandomPoint();
            return this;
        }

        public Builder startPoint(Point startPoint) {            
            this.startPoint = startPoint;
            return this;
        }

        public Builder direction() {
            this.direction = Direction.getRandomDirection();
            return this;
        }

        public Builder direction(Direction direction) {
            this.direction = direction;
            return this;
        }

        public PointsSetter build() throws MalformattedException {
            if(direction == null && startPoint == null) {
                throw new MalformattedException("Direction and point cannot be empty");
            }
            if (direction == null) {
                throw new MalformattedException("Direction cannot be empty");
            }
            if (startPoint == null) {
                throw new MalformattedException("Point cannot be empty");
            }
            return new PointsSetter(this);
        }

    }

    private PointsSetter(Builder builder) {
        this.size = builder.size;
        this.startPoint = builder.startPoint;
        this.direction = builder.direction;
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
            this.points.add(new Point.Builder(startPoint.getRow() - i, startPoint.getColumn()).build());
        }
    }

    private void fillBottom() {
        for (int i = 0; i < size; i++) {
            this.points.add(new Point.Builder(startPoint.getRow() + i, startPoint.getColumn()).build());
        }
    }

    private void fillRight() {
        for (int i = 0; i < size; i++) {
            this.points.add(new Point.Builder(startPoint.getRow(), startPoint.getColumn() + i).build());
        }
    }

    private void fillLeft() {
        for (int i = 0; i < size; i++) {
            this.points.add(new Point.Builder(startPoint.getRow(), startPoint.getColumn() - i).build());
        }

    }
}

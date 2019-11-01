package battleship.ship.vavr;

import java.util.Comparator;

import battleship.direction.vavr.Direction;
import battleship.point.vavr.Point;
import battleship.point.vavr.PointSetter;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;

public final class ShipImpl implements Ship {

    public static class Builder {
        private final ShipClass shipClass;
        private final Either<String, List<Point>> points;

        public Builder(ShipClass shipClass) {
            this.shipClass = shipClass;
            Either<String, PointSetter> pointSetter = new PointSetter.Builder(shipClass.getSize()).startPoint()
                    .direction()
                    .build();

            points = pointSetter.isLeft() ? Either.left("Cannot set points")
                    : Either.right(List.ofAll(pointSetter.get()
                    .getPoints()));
        }

        public Builder(ShipClass shipClass, Point startPoint, Direction direction) {
            this.shipClass = shipClass;
            Either<String, PointSetter> pointSetter = new PointSetter.Builder(shipClass.getSize(), startPoint,
                    direction).build();
            points = pointSetter.isLeft() ? Either.left(pointSetter.getLeft())
                    : Either.right(List.ofAll(pointSetter.get()
                    .getPoints()));

        }

        public Either<String, Ship> build() {
            if (points == null) {
                return Either.left("Points are null");
            }
            if (points.isLeft()) {
                return Either.left(points.getLeft());
            }
            return Either.right(new ShipImpl(this));

        }
    }

    private final List<Point> points;
    private final ShipClass shipClass;
    private int health;

    private ShipImpl(Builder builder) {
        this.points = builder.points.get();
        this.shipClass = builder.shipClass;
        this.health = shipClass.getSize();
    }

    @Override
    public ShipClass getShipClass() {
        return shipClass;
    }

    @Override
    public int getSize() {
        return shipClass.getSize();
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public List<Point> getPoints() {
        return points;
    }

    @Override
    public Option<Point> isAt(Point point) {
        Option<Point> opt = points.find(p -> p.equals(point));
        return opt;
    }

    @Override
    public boolean shoot() {
        if (isSunk()) {
            return false;
        }
        health--;
        return true;
    }

    @Override
    public boolean isSunk() {
        return health == 0;
    }

    @Override
    public boolean toCloseTo(Ship other) {
        for (Point otherPoint : other.getPoints()) {
            for (int i = 0; i < shipClass.getSize(); i++) {
                if (points.get(i)
                        .isNeighbor(otherPoint)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Option<Point> getMostTopPosition() {
        return points.minBy(Comparator.comparingInt(Point::getRow));
    }

    @Override
    public Option<Point> getMostBottomPosition() {
        return points.maxBy(Comparator.comparingInt(Point::getRow));
    }

    @Override
    public Option<Point> getMostLeftPosition() {
        return points.minBy(Comparator.comparingInt(Point::getColumn));
    }

    @Override
    public Option<Point> getMostRightPosition() {
        return points.maxBy(Comparator.comparingInt(Point::getColumn));
    }

    @Override
    public String toString() {
        return "[shipClass=" + shipClass + ", health=" + health + ", points=" + points + "]";
    }

}

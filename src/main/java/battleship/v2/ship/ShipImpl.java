package battleship.v2.ship;

import java.util.List;
import java.util.Optional;

import battleship.v2.direction.Direction;
import battleship.v2.exception.DirectionException;
import battleship.v2.exception.MalformedException;
import battleship.v2.exception.PointException;
import battleship.v2.point.Point;
import battleship.v2.point.PointsSetter;

public class ShipImpl implements Ship {

    private final ShipClass shipClass;
    private int health;
    private List<Point> points;

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
    public void turnLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    public void turnRight() {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<Point> isAt(Point point) {
        // TODO Auto-generated method stub
        return null;
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

    public static class Builder {
        private ShipClass shipClass;
        private List<Point> points;

        public Builder(ShipClass shipClass) {
            this.shipClass = shipClass;
        }

        public Builder points() throws DirectionException, PointException {
            PointsSetter setter = new PointsSetter.Builder(shipClass.getSize()).startPoint()
                    .direction()
                    .build();
            this.points = setter.getPoints();
            return this;
        }

        public Builder points(Point startPoint, Direction direction) throws DirectionException, PointException {
            PointsSetter setter = new PointsSetter.Builder(shipClass.getSize()).startPoint(startPoint)
                    .direction(direction)
                    .build();
            this.points = setter.getPoints();
            return this;
        }

        public ShipImpl build() throws MalformedException {
            if (points.isEmpty()) {
                throw new MalformedException("Points must be set");
            }
            return new ShipImpl(this);
        }
    }

    private ShipImpl(Builder builder) {
        this.shipClass = builder.shipClass;
        this.health = builder.shipClass.getSize();
        this.points = builder.points;
    }

}

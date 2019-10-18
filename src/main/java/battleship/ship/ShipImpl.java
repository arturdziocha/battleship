package battleship.ship;

import java.util.List;
import java.util.Optional;

import battleship.direction.Direction;
import battleship.exception.MalformattedException;
import battleship.point.Point;
import battleship.point.PointsSetter;

public class ShipImpl implements Ship {

    private final ShipClass shipClass;
    private int health;
    private List<Point> points;

    public static class Builder {
        private ShipClass shipClass;
        private List<Point> points;       

        public Builder(ShipClass shipClass) {
            this.shipClass = shipClass;
        }

        public Builder points() throws MalformattedException {
            PointsSetter setter = new PointsSetter.Builder(shipClass.getSize()).startPoint()
                    .direction()
                    .build();
            this.points = setter.getPoints();
            return this;
        }

        public Builder points(Point startPoint, Direction direction) throws MalformattedException{
            
                PointsSetter setter = new PointsSetter.Builder(shipClass.getSize()).startPoint(startPoint)
                        .direction(direction)
                        .build();
                this.points = setter.getPoints();         
            
            return this;
        }

        public ShipImpl build() throws MalformattedException {
            if (points.isEmpty()) {
                throw new MalformattedException("Points must be set");
            }
            return new ShipImpl(this);
        }
    }

    private ShipImpl(Builder builder) {
        this.shipClass = builder.shipClass;
        this.health = builder.shipClass.getSize();
        this.points = builder.points;
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
    public Optional<Point> isAt(Point point) {
        return points.stream()
                .filter(p -> point.equals(p))
                .findFirst();
    }

    @Override
    public void shoot() {
        health--;
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
    public int getMostTopPosition() {
        return points.stream()
                .map(Point::getRow)
                .min(Integer::compare)
                .get();
    }

    @Override
    public int getMostBottomPosition() {
        return points.stream()
                .map(Point::getRow)
                .max(Integer::compare)
                .get();
    }

    @Override
    public int getMostLeftPosition() {
        return points.stream()
                .map(Point::getColumn)
                .min(Integer::compare)
                .get();
    }

    @Override
    public int getMostRightPosition() {
        return points.stream()
                .map(Point::getColumn)
                .max(Integer::compare)
                .get();
    }

    @Override
    public String toString() {
        return "[shipClass=" + shipClass + ", health=" + health + ", points=" + points+"]" ;
    }
    

}

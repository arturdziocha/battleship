package battleship.v1.ship;

import java.util.List;
import java.util.Optional;

import battleship.v1.MalformedException;
import battleship.v1.point.Point;
import battleship.v1.point.PointsResolver;

public class ShipImpl implements Ship {
    private int health;
    private PointsResolver resolver;
    private final ShipClass shipClass;
    private List<Point> points;

    public ShipImpl(PointsResolver resolver) throws MalformedException {
        this.resolver = resolver;
        this.shipClass = resolver.getShipClass();
        this.health = resolver.getShipClass()
                .getSize();
        this.points = resolver.resolve();
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
        // TODO Auto-generated method stub
        return null;
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

}

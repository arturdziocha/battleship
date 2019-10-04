package battleship.ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import battleship.cmdImpl.PointImpl;
import battleship.enums.Direction;
import battleship.point.Point;

public class ShipImpl implements Ship {
    private ShipClass shipClass;
    private int size;
    private Direction direction;
    private Point point;
    private List<Point> points;    

    public ShipImpl(ShipClass shipClass, Direction direction, Point point) {
        this.shipClass = shipClass;
        this.size = shipClass.getSize();
        this.direction = direction;
        this.point = point;
        this.points = new ArrayList<>();        
        calculatePoints();

    }

    private void calculatePoints() {
        switch (direction) {
            case Top:
                fillTop();
                break;
            case Bottom:
                fillBottom();
                break;
            case Right:
                fillRight();
                break;
            case Left:
                fillLeft();
                break;
        }

    }

    @Override
    public ShipClass getShipClass() {
        return shipClass;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public Optional<Point> getPointAt(Point p) {
        return points.stream()
                .filter(point -> point.equals(p))
                .findFirst();
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public List<Point> getPoints() {
        return points;
    }

    @Override
    public void shoot(Point point) {
        Optional<Point> optional = getPointAt(point);
        optional.ifPresent(p -> p.hit());
    }

    @Override
    public int getMostTopPosition() {
        return points.stream().map(Point::getRow).min(Integer::compare).get();
    }

    @Override
    public int getMostBottomPosition() {
        return points.stream().map(Point::getRow).max(Integer::compare).get();
    }

    @Override
    public int getMostLeftPosition() {
        return points.stream().map(Point::getColumn).min(Integer::compare).get();
    }

    @Override
    public int getMostRightPosition() {
        return points.stream().map(Point::getColumn).max(Integer::compare).get();
    }

    @Override
    public boolean toCloseTo(Ship other) {
        for (Point otherPoint : other.getPoints()) {
            for (int i = 0; i < size; i++) {
                if (points.get(i)
                        .isNeighbor(otherPoint)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isSunk() {
        return points.stream()
                .allMatch(p -> p.isHit());
    }

    private void fillTop() {
        for (int i = 0; i < size; i++) {
            this.points.add(new PointImpl(getPoint().getRow() - i, getPoint().getColumn()));
        }
    }

    private void fillBottom() {
        for (int i = 0; i < size; i++) {
            this.points.add(new PointImpl(getPoint().getRow() + i, getPoint().getColumn()));
        }
    }

    private void fillRight() {
        for (int i = 0; i < size; i++) {
            this.points.add(new PointImpl(getPoint().getRow(), getPoint().getColumn() + i));
        }
    }

    private void fillLeft() {
        for (int i = 0; i < size; i++) {
            this.points.add(new PointImpl(getPoint().getRow(), getPoint().getColumn() - i));
        }

    }

}

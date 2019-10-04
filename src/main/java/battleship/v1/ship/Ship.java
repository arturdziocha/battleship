package battleship.v1.ship;

import java.util.List;
import java.util.Optional;

import battleship.enums.Direction;
import battleship.point.Point;

public interface Ship {
    ShipClass getShipClass();

    abstract int getSize();

    Direction getDirection();

    Optional<Point> getPointAt(Point point);

    Point getPoint();

    List<Point> getPoints();     

    boolean isSunk();

    boolean toCloseTo(Ship other);

    void shoot(Point point);

    int getMostTopPosition();

    int getMostBottomPosition();

    int getMostLeftPosition();

    int getMostRightPosition();
}

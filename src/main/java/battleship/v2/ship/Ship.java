package battleship.v2.ship;

import java.util.List;
import java.util.Optional;

import battleship.v2.point.Point;

public interface Ship {
    ShipClass getShipClass();

    int getSize();

    int getHealth();

    void turnRight();

    void turnLeft();

    Optional<Point> isAt(Point point);

    List<Point> getPoints();

    boolean isSunk();

    boolean toCloseTo(Ship other);

    void shoot(Point point);
}

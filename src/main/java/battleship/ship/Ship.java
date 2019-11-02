package battleship.ship;

import battleship.point.Point;
import io.vavr.collection.List;
import io.vavr.control.Option;

public interface Ship {
    ShipClass getShipClass();

    int getSize();

    int getHealth();

    List<Point> getPoints();

    Option<Point> isAt(Point point);

    boolean shoot();

    boolean isSunk();

    boolean toCloseTo(Ship other);

    Option<Point> getMostTopPosition();

    Option<Point> getMostBottomPosition();

    Option<Point> getMostLeftPosition();

    Option<Point> getMostRightPosition();

}

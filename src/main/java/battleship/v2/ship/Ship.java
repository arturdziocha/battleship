package battleship.v2.ship;

import java.util.List;

import battleship.v2.point.Point;

public interface Ship {
    ShipClass getShipClass();

    int getSize();

    int getHealth();    

    List<Point> getPoints();
    
    boolean shoot(Point point);

    boolean isSunk();

    boolean toCloseTo(Ship other);

    int getMostTopPosition();

    int getMostBottomPosition();

    int getMostLeftPosition();

    int getMostRightPosition();
    
}
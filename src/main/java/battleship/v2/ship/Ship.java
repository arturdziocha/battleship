package battleship.v2.ship;

import java.util.List;
import java.util.Optional;

import battleship.v2.point.Point;

public interface Ship {
    ShipClass getShipClass();

    int getSize();

    int getHealth();    

    List<Point> getPoints();
    
    Optional<Point> isAt(Point point);
    
    void shoot();

    boolean isSunk();

    boolean toCloseTo(Ship other);

    int getMostTopPosition();

    int getMostBottomPosition();

    int getMostLeftPosition();

    int getMostRightPosition();
    
}

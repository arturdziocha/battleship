package battleship.v1.ship;

import java.util.List;
import java.util.Optional;

import battleship.v1.point.Point;


public interface Ship {
    ShipClass getShipClass();

    int getSize();
    
    int getHealth();

    Optional<Point> isAt(Point point);    

    List<Point> getPoints();     

    boolean isSunk();

    boolean toCloseTo(Ship other);

    void shoot(Point point);
}

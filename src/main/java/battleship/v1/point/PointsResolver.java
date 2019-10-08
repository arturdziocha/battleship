package battleship.v1.point;

import battleship.v1.MalformedException;
import battleship.v1.ship.ShipClass;

import java.util.List;

public interface PointsResolver {
    ShipClass getShipClass();
    Point getStartPoint();
    Direction getDirection();
	List<Point> resolve() throws MalformedException;
}

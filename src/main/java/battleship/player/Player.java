package battleship.player;

import java.util.Map;

import battleship.exception.MalformattedException;
import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.Ship;

public interface Player {

    boolean hasLost();

    Point prepareShot() throws MalformattedException;

    void setShot(Point point, PointStatus pointStatus);

    void setShotSunk(Ship ship);

    boolean isAlreadyShooted(Point point);

    Map<Point, PointStatus> getShots();

    Fleet getFleet();

    String getName();

}

package battleship.player;

import java.util.Optional;

import battleship.exception.MalformattedException;
import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.Ship;

public interface Player {
    Optional<Ship> shootToFleet(Point point);

    boolean hasLost();

    Point prepareShot() throws MalformattedException;

    void setShot(Point point, PointStatus pointStatus);

    void setShotSunk(Ship ship);

    boolean isAlreadyShooted(Point point);

    PointStatus[][] getShots();

    Fleet getFleet();

}

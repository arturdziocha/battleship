package battleship.player;

import java.util.List;
import java.util.Optional;

import battleship.exception.NotAllShipsPlacedException;
import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.Ship;
import battleship.ship.ShipClass;

public interface Player {    
    List<ShipClass> shipsToPlace();
    Optional<Ship> shootToFleet(Point point) throws NotAllShipsPlacedException;
    boolean hasLost();
    boolean isAlreadyShooted(Point point);
    void setShot(Point point, PointStatus pointStatus);
    void setShotSunk(Ship ship);
    PointStatus[][] getShots();
    Fleet getFleet();
}

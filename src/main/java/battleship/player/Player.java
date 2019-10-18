package battleship.player;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import battleship.exception.NotAllShipsPlacedException;
import battleship.fleet.Fleet;
import battleship.fleet.FleetImpl;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.Ship;

public abstract class Player {
    protected Map<Point, PointStatus> shots;
    protected Fleet fleet;
    protected String name;

    public Player() {
        fleet = new FleetImpl();
    }

    public Optional<Ship> shootToFleet(Point point) throws NotAllShipsPlacedException {
        if (!fleet.isAllShipsPlaced()) {
            throw new NotAllShipsPlacedException("Not all ships placed");
        }
        return fleet.shipAt(point);
    }
    public boolean hasLost() {
        return fleet.allShipsSunk();
    }
    public boolean isAlreadyShooted(Point point) {
        return shots.containsKey(point);
    }
    public List<Ship> shipsToPlace(){
        return null;
    }

}

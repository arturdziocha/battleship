package battleship.player;

import java.util.Map;
import java.util.Optional;

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
    public Optional<Ship> receiveShot(Point point){
        return fleet.shipAt(point);
    }
    
}

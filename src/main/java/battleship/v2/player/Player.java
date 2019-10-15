package battleship.v2.player;

import java.util.Map;
import java.util.Optional;

import battleship.v2.fleet.Fleet;
import battleship.v2.fleet.FleetImpl;
import battleship.v2.point.Point;
import battleship.v2.point.PointStatus;
import battleship.v2.ship.Ship;

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

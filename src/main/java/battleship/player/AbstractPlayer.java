package battleship.player;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import battleship.exception.MalformattedException;
import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.Ship;

public abstract class AbstractPlayer implements Player {
    private Map<Point, PointStatus> shots = new TreeMap<>();
    Fleet fleet;
    String name;

    @Override
    public boolean hasLost() {
        return fleet.isAllShipsSunk();
    }

    @Override
    public void setShot(Point point, PointStatus pointStatus) {
        if (point.isInsideBoard()) {
            if (shots.containsKey(point)) {
                shots.replace(point, pointStatus);
            } else {
                shots.put(point, pointStatus);
            }
        }
    }

    @Override
    public void setShotSunk(Ship ship) {
        Set<Point> occupiedPoints = new TreeSet<>();

        ship.getPoints()
                .forEach(point -> {
                    setShot(point, PointStatus.SUNK);
                    point.calculateNeighbors()
                            .forEach(neighbor -> occupiedPoints.add(neighbor));
                });
        occupiedPoints.removeAll(ship.getPoints());
        occupiedPoints.forEach(point -> setShot(point, PointStatus.OCCUPIED));

//        ship.getPoints()
//                .forEach(point -> {
//                    point.calculateNeighbors()
//                            .forEach(neighbor -> setShot(neighbor, PointStatus.OCCUPIED));
//                });

//        ship.getPoints()
//                .forEach(point -> setShot(point, PointStatus.SUNK));
    }

    @Override
    public boolean isAlreadyShooted(Point point) {
        return shots.containsKey(point);
    }

    @Override
    public Map<Point, PointStatus> getShots() {
        return shots;
    }

    @Override
    public Fleet getFleet() {
        return fleet;
    }

    @Override
    public abstract String getName();

    @Override
    public abstract Point prepareShot() throws MalformattedException;
}

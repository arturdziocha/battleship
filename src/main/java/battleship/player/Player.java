package battleship.player;


import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.Ship;
import io.vavr.collection.Map;
import io.vavr.control.Either;

public interface Player {

    boolean hasLost();

    Either<String, Point> prepareShot();

    void setShot(Point point, PointStatus pointStatus);

    void setShotSunk(Ship ship);

    boolean isAlreadyShooted(Point point);

    Map<Point, PointStatus> getShots();

    Fleet getFleet();

    String getName();

}

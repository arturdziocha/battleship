package battleship.player.vavr;


import battleship.fleet.vavr.Fleet;
import battleship.point.vavr.Point;
import battleship.point.vavr.PointStatus;
import battleship.ship.vavr.Ship;
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

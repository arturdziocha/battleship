package battleship.v2.fleet;

import java.util.List;
import java.util.Optional;

import battleship.v2.exception.DirectionException;
import battleship.v2.exception.MalformedException;
import battleship.v2.exception.PointException;
import battleship.v2.point.Point;
import battleship.v2.ship.Ship;


public interface Fleet {
    final int SIZE = 10;

    boolean placeShip(Ship ship);

    void placeShipsRandom() throws MalformedException, DirectionException, PointException;

    List<Ship> getShips();

    boolean isAllShipsPlaced();

    Optional<Ship> shipAt(Point point);
}

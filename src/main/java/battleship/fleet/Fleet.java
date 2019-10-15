package battleship.fleet;

import java.util.List;
import java.util.Optional;

import battleship.exception.DirectionException;
import battleship.exception.MalformedException;
import battleship.exception.PointException;
import battleship.point.Point;
import battleship.ship.Ship;


public interface Fleet {
    final int SIZE = 10;

    boolean placeShip(Ship ship);

    void placeShipsRandom() throws MalformedException, DirectionException, PointException;

    List<Ship> getShips();

    boolean isAllShipsPlaced();

    Optional<Ship> shipAt(Point point);
}

package battleship.v2.fleet;

import java.util.List;
import java.util.Optional;

import battleship.v0.point.Point;
import battleship.v0.ship.Ship;

public interface Fleet {
    final int SIZE = 10;

    boolean placeShip(Ship ship);

    boolean placeAllShips(List<Ship> ships);

    List<Ship> getShips();

    boolean isAllShipsPlaced();

    Optional<Ship> shoot(Point point);
}

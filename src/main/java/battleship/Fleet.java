package battleship;

import java.util.List;
import java.util.Optional;

import battleship.ship.Ship;

public interface Fleet {
    final int SIZE = 10;
    boolean placeShip(Ship ship);
    void placeShipsRandom();
    List<Ship> getShips();
    boolean isAllShipsPlaced();
    Optional<Ship> shipAt(Point point);
}

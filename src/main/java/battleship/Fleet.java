package battleship;

import java.util.List;
import java.util.Optional;

public interface Fleet {
    final int SIZE = 10;
    boolean placeShip(Ship ship);
    void placeShipsRandom();
    List<Ship> getShips();
    boolean isAllShipsPlaced();
    Optional<Ship> shipAt(Point point);
}

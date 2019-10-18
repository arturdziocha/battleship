package battleship.fleet;

import java.util.List;
import java.util.Optional;

import battleship.exception.MalformattedException;
import battleship.exception.ShipOverlapException;
import battleship.point.Point;
import battleship.ship.Ship;


public interface Fleet {    
    final int SIZE = 10;

    void placeShip(Ship ship) throws ShipOverlapException ;

    void placeShipsRandom()  throws MalformattedException, ShipOverlapException ;

    List<Ship> getShips();

    boolean isAllShipsPlaced();

    Optional<Ship> shipAt(Point point);
    
    List<Ship> shipsToPlace();

    boolean allShipsSunk();
}

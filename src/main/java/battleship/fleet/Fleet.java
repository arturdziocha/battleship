package battleship.fleet;

import battleship.point.Point;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface Fleet {
	Either<String, List<Ship>> placeShip(Ship ship);

	void placeAllShipsRandom();

	boolean isAllShipsPlaced();

	List<Ship> getShips();

	List<ShipClass> shipsToPlace();
	
	Option<Ship> shipAt(Point point);
	
	boolean isAllShipsSunk();

}

package battleship.fleet.vavr;

import battleship.point.vavr.Point;
import battleship.ship.vavr.Ship;
import battleship.ship.vavr.ShipClass;
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

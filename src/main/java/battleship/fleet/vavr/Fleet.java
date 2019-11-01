package battleship.fleet.vavr;

import battleship.point.vavr.Point;
import battleship.ship.vavr.Ship;
import battleship.ship.vavr.ShipClass;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface Fleet {
	Either<String, List<Ship>> placeShip(Ship ship);

	Either<String, List<Ship>> placeAllShipsRandom();

	boolean isAllShipsPlaced();

	List<Ship> getShips();

	Set<ShipClass> shipsToPlace();
	
	Option<Ship> shipAt(Point point);
	
	boolean isAllShipsSunk();

}

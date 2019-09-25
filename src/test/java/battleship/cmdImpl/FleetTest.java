package battleship.cmdImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.Fleet;
import battleship.Point;
import battleship.cmdImpl.FleetImpl;
import battleship.cmdImpl.PointImpl;
import battleship.cmdImpl.ShipImpl;
import battleship.enums.Direction;
import battleship.enums.ShipClass;
import battleship.ship.Ship;

public class FleetTest {
	private Fleet fleet;

	@BeforeEach
	void setUp() {
		fleet = new FleetImpl();
	}

	@Test
	void placeShipReturnTrueWhenShipPointsInsideBoard() {
		Ship ship = new ShipImpl(ShipClass.Destroyer, Direction.Bottom, new PointImpl(7, 8));
		assertTrue(fleet.placeShip(ship));
	}

	@Test
	void placeShipReturnFalseWhenShipPointBottomOutsideBoard() {
		Ship ship = new ShipImpl(ShipClass.Destroyer, Direction.Bottom, new PointImpl(8, 8));
		assertFalse(fleet.placeShip(ship));
	}

	@Test
	void placeShipReturnFalseWhenShipPointTopOutsideBoard() {
		Ship ship = new ShipImpl(ShipClass.Destroyer, Direction.Top, new PointImpl(1, 8));
		assertFalse(fleet.placeShip(ship));
	}

	@Test
	void placeShipReturnFalseWhenShipPointRigthOutsideBoard() {
		Ship ship = new ShipImpl(ShipClass.Destroyer, Direction.Right, new PointImpl(1, 8));
		assertFalse(fleet.placeShip(ship));
	}

	@Test
	void placeShipReturnFalseWhenShipPointLeftOutsideBoard() {
		Ship ship = new ShipImpl(ShipClass.Destroyer, Direction.Left, new PointImpl(1, 1));
		assertFalse(fleet.placeShip(ship));
	}

	@Test
	void shouldReturnTrueWhenShipIsNotToCloseShips() {
		Ship ship = new ShipImpl(ShipClass.Destroyer, Direction.Right, new PointImpl(1, 1));
		Ship notToClose = new ShipImpl(ShipClass.Submarine, Direction.Right, new PointImpl(3, 1));
		assertTrue(fleet.placeShip(ship));
		assertTrue(fleet.placeShip(notToClose));
	}

	@Test
	void shouldReturnFalseWhenShipToCloseShips() {
		Ship ship = new ShipImpl(ShipClass.Destroyer, Direction.Right, new PointImpl(0, 1));
		Ship ship2 = new ShipImpl(ShipClass.Submarine, Direction.Bottom, new PointImpl(1, 5));
		Ship toClose = new ShipImpl(ShipClass.Battleship, Direction.Right, new PointImpl(3, 1));

		assertTrue(fleet.placeShip(ship));
		assertTrue(fleet.placeShip(ship2));
		assertFalse(fleet.placeShip(toClose));
	}

	@Test
	void shouldReturnFalseWhenShipAlreadyPlaced() {
		Ship ship = new ShipImpl(ShipClass.Destroyer, Direction.Right, new PointImpl(0, 1));
		Ship ship2 = new ShipImpl(ShipClass.Destroyer, Direction.Bottom, new PointImpl(5, 1));
		fleet.placeShip(ship);
		assertFalse(fleet.placeShip(ship2));
	}

	@Test
	void shouldReturnTrueWhenAllShipsPlaced() {
		Ship[] ships = { new ShipImpl(ShipClass.Barca, Direction.Bottom, new PointImpl(0, 0)),
		        new ShipImpl(ShipClass.PatrolBoat, Direction.Bottom, new PointImpl(0, 2)),
		        new ShipImpl(ShipClass.Submarine, Direction.Bottom, new PointImpl(0, 4)),
		        new ShipImpl(ShipClass.Destroyer, Direction.Bottom, new PointImpl(0, 6)),
		        new ShipImpl(ShipClass.Battleship, Direction.Bottom, new PointImpl(0, 8)),
		        new ShipImpl(ShipClass.Carrier, Direction.Right, new PointImpl(7, 0)) };
		for (Ship ship : ships) {
			fleet.placeShip(ship);
		}
		assertTrue(fleet.isAllShipsPlaced());
	}

	@Test
	void shouldReturnFalseWhenNotAllShipsPlaced() {
		Ship[] ships = { new ShipImpl(ShipClass.Barca, Direction.Bottom, new PointImpl(0, 0)),
		        new ShipImpl(ShipClass.Submarine, Direction.Bottom, new PointImpl(0, 4)),
		        new ShipImpl(ShipClass.Destroyer, Direction.Bottom, new PointImpl(0, 6)),
		        new ShipImpl(ShipClass.Battleship, Direction.Bottom, new PointImpl(0, 8)),
		        new ShipImpl(ShipClass.Carrier, Direction.Right, new PointImpl(7, 0)) };
		for (Ship ship : ships) {
			fleet.placeShip(ship);
		}
		assertFalse(fleet.isAllShipsPlaced());
	}

	@Test
	void getAtAndShipExistsAtPointReturnOptionalOfShip() {
		Ship ship = new ShipImpl(ShipClass.Destroyer, Direction.Bottom, new PointImpl(7, 4));
		Point point = new PointImpl(8, 4);
		fleet.placeShip(ship);
		assertTrue(fleet.shipAt(new PointImpl(8, 4))
		        .isPresent());
		assertEquals(ship, fleet.shipAt(point)
		        .get());
	}

	@Test
	void shouldReturnOptionalOfShipWhenGetAtAndShipNotExistsAtPoint() {
		Ship ship = new ShipImpl(ShipClass.Destroyer, Direction.Bottom, new PointImpl(7, 4));
		Point point = new PointImpl(1, 4);
		fleet.placeShip(ship);
		assertFalse(fleet.shipAt(point)
		        .isPresent());
	}

	@Test
	void shouldReturnTrueWhenPlaceAllShipsRandom() {
		fleet.placeShipsRandom();
		assertTrue(fleet.isAllShipsPlaced());
	}

}

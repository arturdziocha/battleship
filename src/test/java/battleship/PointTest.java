package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointTest {
	private Point point;
	private Ship ship;
	@BeforeEach
	void setUp() throws Exception {
		this.point = new Point();
		this.ship = new Ship(ShipClass.Battleship);
	}

	@Test
	void whenInitializedShipIsNull() {
		assertNull(point.getShip());
	}
	@Test
	void whenInitializedPointStatusIsEmpty() {
		assertEquals(PointStatus.Empty, point.getStatus());
	}
	@Test
	void whenPlaceShipSetStatusOccupiedAndShipPlaced() throws AlreadyOccupiedException {
		Ship ship = new Ship(ShipClass.Battleship);
		point.placeShip(ship);		
		assertEquals(PointStatus.Ship, point.getStatus());
		assertEquals(ship, point.getShip());
	}
	@Test
	void whenPlaceShipStatusOccupiedThrowsException() throws AlreadyOccupiedException {
		point.placeShip(ship);
		assertThrows(AlreadyOccupiedException.class, ()->point.placeShip(new Ship(ShipClass.Barca)));
	}
	@Test
	void whenShotAndShipIsSunkThrowsException() throws AlreadySunkEception {
		ship.shot();
		ship.shot();
		ship.shot();
		assertThrows(AlreadySunkEception.class, ()->point.receiveShot());
		
	}
	@Test
	void whenShotAndStatusEmptySetStatusMISS() throws AlreadySunkEception {
		point.receiveShot();
		assertEquals(PointStatus.Miss, point.getStatus());
	}
	@Test
	void whenShotAndStatusOccupiedAndShipSetStatusHIT() throws AlreadySunkEception, AlreadyOccupiedException {
		point.placeShip(ship);
		point.receiveShot();
		assertEquals(PointStatus.Hit, point.getStatus());		
	}

}

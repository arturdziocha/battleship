package battleship;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.enums.ShipClass;
import battleship.interfaces.Point;
import battleship.interfaces.Ship;

class ShipImplTest {
	private Ship ship;
	private ShipClass shipClass;
	private Point point;
	private Direction direction;

	@BeforeEach
	void setUp() throws Exception {
		direction = Direction.North;
		point = new PointImpl(5, 6);
		shipClass = ShipClass.Destroyer;
		ship = new ShipImpl(shipClass, direction, point);

	}

	@Test
	void whenInstatntiatedThenShipClassIsSet() {
		ShipClass sClass = ShipClass.Destroyer;
		assertEquals(sClass, ship.getShipClass());
	}

	@Test
	void whenInstantiatedThenSizeIsSet() {
		assertEquals(3, ship.getSize());
	}

	@Test
	void whenInstantiatedThenDirectionIsSet() {
		assertEquals(Direction.North, ship.getDirection());
	}

	@Test
	void whenInstantiatedThenPointIsSet() {
		assertEquals(point, ship.getPoint());
	}

	@Test
	void northPointsBarcaCreated() {
		Point[] points = { new PointImpl(5, 6) };
		ship = new ShipImpl(ShipClass.Barca, direction, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void northPointsPatrolBoatCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(4, 6) };
		ship = new ShipImpl(ShipClass.PatrolBoat, direction, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void northPointsSubmarineCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(4, 6), new PointImpl(3, 6) };
		ship = new ShipImpl(ShipClass.Submarine, direction, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void northPointsDestroyerCreated() {
		Point[] points = { new PointImpl(3, 6), new PointImpl(4, 6), new PointImpl(5, 6) };
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void northPointsBattleshipCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(4, 6), new PointImpl(3, 6), new PointImpl(2, 6) };
		ship = new ShipImpl(ShipClass.Battleship, direction, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void northPointsCarrierCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(4, 6), new PointImpl(3, 6), new PointImpl(2, 6),
		        new PointImpl(1, 6) };
		ship = new ShipImpl(ShipClass.Carrier, direction, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void southPointsBarcaCreated() {
		Point[] points = { new PointImpl(5, 6) };
		ship = new ShipImpl(ShipClass.Barca, Direction.South, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void southPointsDestroyerCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(6, 6), new PointImpl(7, 6) };
		ship = new ShipImpl(ShipClass.Destroyer, Direction.South, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void southPointsCarrierCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(6, 6), new PointImpl(7, 6), new PointImpl(8, 6),
		        new PointImpl(9, 6) };
		ship = new ShipImpl(ShipClass.Carrier, Direction.South, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void eastPointsBarcaCreated() {
		Point[] points = { new PointImpl(5, 6) };
		ship = new ShipImpl(ShipClass.Barca, Direction.East, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void eastPointsDestroyerCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(5, 5), new PointImpl(5, 4) };
		ship = new ShipImpl(ShipClass.Destroyer, Direction.East, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void eastPointsCarrierCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(5, 5), new PointImpl(5, 4), new PointImpl(5, 3),
		        new PointImpl(5, 2) };
		ship = new ShipImpl(ShipClass.Carrier, Direction.East, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void westPointsBarcaCreated() {
		Point[] points = { new PointImpl(5, 6) };
		ship = new ShipImpl(ShipClass.Barca, Direction.West, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void westPointsDestroyerCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(5, 7), new PointImpl(5, 8) };
		ship = new ShipImpl(ShipClass.Destroyer, Direction.West, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void westPointsCarrierCreated() {
		Point[] points = { new PointImpl(5, 5), new PointImpl(5, 6), new PointImpl(5, 7), new PointImpl(5, 8),
		        new PointImpl(5, 9) };
		ship = new ShipImpl(ShipClass.Carrier, Direction.West, new PointImpl(5, 5));
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void shipShotShouldSetPointHit() {
		Point p = new PointImpl(4, 6);
		ship.shoot(p);
		assertTrue(ship.getPointAt(p)
		        .isPresent());
		assertTrue(ship.getPointAt(p)
		        .get()
		        .isHit());
	}

	@Test
	void isSunkReturnFalseWithoutShot() {
		ship.shoot(new PointImpl(4, 6));
		assertFalse(ship.isSunk());
	}

	@Test
	void isSunkReturnFalseWithOneShot() {
		ship.shoot(new PointImpl(4, 6));
		assertFalse(ship.isSunk());
	}

	@Test
	void isSunkReturnFalseWithTwoShots() {
		ship.shoot(new PointImpl(3, 6));
		ship.shoot(new PointImpl(5, 6));
		assertFalse(ship.isSunk());
	}

	@Test
	void isSunkReturnTrue() {
		ship.shoot(new PointImpl(4, 6));
		ship.shoot(new PointImpl(5, 6));
		ship.shoot(new PointImpl(3, 6));
		assertTrue(ship.isSunk());
	}

	@Test
	void otherShipIsNorthAndToCloseReturnTrue() {
		Ship toCloseShip = new ShipImpl(shipClass, direction, new PointImpl(6, 6));
		assertTrue(ship.toCloseTo(toCloseShip));
	}

	@Test
	void otherShipIsNorthAndNotToCloseReturnFalse() {
		Ship notToCloseShip = new ShipImpl(shipClass, direction, new PointImpl(5, 4));
		assertFalse(ship.toCloseTo(notToCloseShip));
	}
	@Test
	void otherShipIsSouthAndToCloseReturnTrue() {
		Ship toCloseShip = new ShipImpl(shipClass, Direction.South, new PointImpl(6, 5));
		assertTrue(ship.toCloseTo(toCloseShip));
	}
	@Test
	void otherShipIsSouthAndNotToCloseReturnFalse() {
		Ship notToCloseShip = new ShipImpl(shipClass, Direction.South, new PointImpl(7, 5));
		assertFalse(ship.toCloseTo(notToCloseShip));
	}
}

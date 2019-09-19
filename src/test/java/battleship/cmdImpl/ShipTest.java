package battleship.cmdImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.Point;
import battleship.Ship;
import battleship.cmdImpl.PointImpl;
import battleship.cmdImpl.ShipImpl;
import battleship.enums.Direction;
import battleship.enums.ShipClass;

class ShipTest {
	private Ship ship;
	private ShipClass shipClass;
	private Point point;
	private Direction direction;

	@BeforeEach
	void setUp() throws Exception {
		direction = Direction.Top;
		point = new PointImpl(5, 6);
		shipClass = ShipClass.Destroyer;
		ship = new ShipImpl(shipClass, direction, point);

	}

	@Test
	void whenInstantiatedThenShipClassIsSet() {
		ShipClass sClass = ShipClass.Destroyer;
		assertEquals(sClass, ship.getShipClass());
	}

	@Test
	void whenInstantiatedThenSizeIsSet() {
		assertEquals(3, ship.getSize());
	}

	@Test
	void whenInstantiatedThenDirectionIsSet() {
		assertEquals(Direction.Top, ship.getDirection());
	}

	@Test
	void whenInstantiatedThenPointIsSet() {
		assertEquals(point, ship.getPoint());
	}

	@Test
	void topPointsBarcaCreated() {
		Point[] points = { new PointImpl(5, 6) };
		ship = new ShipImpl(ShipClass.Barca, direction, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void topPointsPatrolBoatCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(4, 6) };
		ship = new ShipImpl(ShipClass.PatrolBoat, direction, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void topPointsSubmarineCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(4, 6), new PointImpl(3, 6) };
		ship = new ShipImpl(ShipClass.Submarine, direction, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void topPointsDestroyerCreated() {
		Point[] points = { new PointImpl(3, 6), new PointImpl(4, 6), new PointImpl(5, 6) };
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void topPointsBattleshipCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(4, 6), new PointImpl(3, 6), new PointImpl(2, 6) };
		ship = new ShipImpl(ShipClass.Battleship, direction, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void topPointsCarrierCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(4, 6), new PointImpl(3, 6), new PointImpl(2, 6),
		        new PointImpl(1, 6) };
		ship = new ShipImpl(ShipClass.Carrier, direction, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void bottomPointsBarcaCreated() {
		Point[] points = { new PointImpl(5, 6) };
		ship = new ShipImpl(ShipClass.Barca, Direction.Bottom, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void bottomPointsDestroyerCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(6, 6), new PointImpl(7, 6) };
		ship = new ShipImpl(ShipClass.Destroyer, Direction.Bottom, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void bottomPointsCarrierCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(6, 6), new PointImpl(7, 6), new PointImpl(8, 6),
		        new PointImpl(9, 6) };
		ship = new ShipImpl(ShipClass.Carrier, Direction.Bottom, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void rightPointsBarcaCreated() {
		Point[] points = { new PointImpl(5, 6) };
		ship = new ShipImpl(ShipClass.Barca, Direction.Right, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void rightPointsDestroyerCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(5, 7), new PointImpl(5, 8) };
		ship = new ShipImpl(ShipClass.Destroyer, Direction.Right, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void rightPointsCarrierCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(5, 7), new PointImpl(5, 8), new PointImpl(5, 9),
		        new PointImpl(5, 10) };
		ship = new ShipImpl(ShipClass.Carrier, Direction.Right, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void leftPointsBarcaCreated() {
		Point[] points = { new PointImpl(5, 6) };
		ship = new ShipImpl(ShipClass.Barca, Direction.Left, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void leftPointsDestroyerCreated() {
		Point[] points = { new PointImpl(5, 6), new PointImpl(5, 5), new PointImpl(5, 4) };
		ship = new ShipImpl(ShipClass.Destroyer, Direction.Left, point);
		assertThat(ship.getPoints(), containsInAnyOrder(points));
	}

	@Test
	void leftPointsCarrierCreated() {
		Point[] points = { new PointImpl(5, 5), new PointImpl(5, 4), new PointImpl(5, 3), new PointImpl(5, 2),
		        new PointImpl(5, 1) };
		ship = new ShipImpl(ShipClass.Carrier, Direction.Left, new PointImpl(5, 5));
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
	void otherShipIsTopAndToCloseReturnTrue() {
		Ship toCloseShip = new ShipImpl(shipClass, direction, new PointImpl(6, 6));
		assertTrue(ship.toCloseTo(toCloseShip));
	}

	@Test
	void otherShipIsTopAndNotToCloseReturnFalse() {
		Ship notToCloseShip = new ShipImpl(shipClass, direction, new PointImpl(5, 4));
		assertFalse(ship.toCloseTo(notToCloseShip));
	}

	@Test
	void otherShipIsBottomAndToCloseReturnTrue() {
		Ship toCloseShip = new ShipImpl(shipClass, Direction.Bottom, new PointImpl(6, 5));
		assertTrue(ship.toCloseTo(toCloseShip));
	}

	@Test
	void otherShipIsBottomAndNotToCloseReturnFalse() {
		Ship notToCloseShip = new ShipImpl(shipClass, Direction.Bottom, new PointImpl(7, 5));
		assertFalse(ship.toCloseTo(notToCloseShip));
	}

	@Test
	void otherShipIsRightAndNotToCloseReturnFalse() {
		Ship notToCloseShip = new ShipImpl(shipClass, Direction.Right, new PointImpl(7, 8));
		assertFalse(ship.toCloseTo(notToCloseShip));
	}

	@Test
	void shipIsRightAndotherShipIsBottomAndNotToCloseReturnFalse() {
		Ship ship = new ShipImpl(ShipClass.Destroyer, Direction.Right, new PointImpl(0, 1));
		Ship ship2 = new ShipImpl(ShipClass.Destroyer, Direction.Bottom, new PointImpl(1, 5));		
		assertFalse(ship.toCloseTo(ship2));
	}

	@Test
	void getMostTopPosition() {
		assertThat(ship.getMostTopPosition(), is(3));
	}

	@Test
	void getMostBottomPosition() {
		assertThat(ship.getMostBottomPosition(), is(5));
	}

	@Test
	void getMostLeftPosition() {
		assertThat(ship.getMostLeftPosition(), is(6));
	}

	@Test
	void getMostLeftPositionShiDirectionIsLeft() {
		ship = new ShipImpl(shipClass, Direction.Left, point);
		assertThat(ship.getMostLeftPosition(), is(4));
	}

	@Test
	void getMostRightPosition() {
		assertThat(ship.getMostRightPosition(), is(6));
	}

	@Test
	void getMostRightPositionShipDirectionIsRight() {
		ship = new ShipImpl(shipClass, Direction.Right, point);
		assertThat(ship.getMostRightPosition(), is(8));
	}
}

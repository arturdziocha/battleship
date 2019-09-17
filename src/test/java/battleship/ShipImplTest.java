package battleship;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

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
	void nortPointsBarcaCreated() {
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
}

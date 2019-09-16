package battleship;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

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
	void testNorthPointsCreated() {
		List<Point> points = Arrays.asList(new PointImpl(3, 6), new PointImpl(4, 6), new PointImpl(5, 6));

		assertThat(ship.getPoints(), containsInAnyOrder(ship.getPoints()));
	}

}

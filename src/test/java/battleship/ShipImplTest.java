package battleship;

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
	void whenInstatntiatedThenShipClassIsStored(){
		ShipClass sClass = ShipClass.Destroyer;
		assertEquals(sClass, ship.getShipClass());
	}

	@Test
	void testNorthPointsCreated() {
		List<Point> points = Arrays.asList(new PointImpl(3, 6), new PointImpl(4, 6), new PointImpl(5, 6));
		int i = 0;
		for(Point p : ship.getPoints()) {
			assertEquals(p.getRow(), points.get(i).getRow());
			assertEquals(p.getColumn(), points.get(i).getColumn());
			i++;
		}
	}
 		

}

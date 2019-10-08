package battleship.v1.ship;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.v1.MalformedException;
import battleship.v1.point.InputPointsResolver;
import battleship.v1.point.Point;
import battleship.v1.point.PointImpl;
import battleship.v1.point.PointsResolver;

class ShipTest {
    private Ship ship;
    PointsResolver resolver;
    private ShipClass shipClass;

    @BeforeEach
    void setUp() throws Exception {
        shipClass = ShipClass.DESTROYER;
        resolver = new InputPointsResolver(ShipClass.DESTROYER, "B4", "l");
        ship = new ShipImpl(resolver);

    }

    @Test
    void whenInstantiatedThenShipClassIsSet() {
        assertEquals(shipClass, ship.getShipClass());
    }

    @Test
    void whenInstantiatedThenSizeIsSet() {
        assertEquals(3, ship.getSize());
    }

    @Test
    void whenInstantiatedThenHealthIsSet() {
        assertEquals(3, ship.getHealth());
    }

    @Test
    void testBarcaPointsCreation() throws MalformedException {
        Point[] points = { new PointImpl(4, 6) };
        resolver = new InputPointsResolver(ShipClass.BARCA, "G5", "l");
        ship = new ShipImpl(resolver);        
        assertThat(ship.getPoints(), containsInAnyOrder(points));
    }

    @Test
    void testPatrolBoatPointsCreation() throws MalformedException {
        Point[] points = { new PointImpl(4, 6), new PointImpl(5, 6) };
        resolver = new InputPointsResolver(ShipClass.PATROL_BOAT, "G5", "d");
        ship = new ShipImpl(resolver);        
        assertThat(ship.getPoints(), containsInAnyOrder(points));
    }
    @Test
    void testDestroyerPointsCreation() throws MalformedException {
        Point[] points = { new PointImpl(3,5), new PointImpl(3,6), new PointImpl(3,7) };
        resolver = new InputPointsResolver(ShipClass.DESTROYER, "F4", "r");
        ship = new ShipImpl(resolver);        
        assertThat(ship.getPoints(), containsInAnyOrder(points));
    }
}

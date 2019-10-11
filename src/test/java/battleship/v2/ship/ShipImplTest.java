package battleship.v2.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.Test;import battleship.v0.cmdImpl.PointImpl;
import battleship.v2.direction.Direction;
import battleship.v2.exception.DirectionException;
import battleship.v2.exception.MalformedException;
import battleship.v2.exception.PointException;
import battleship.v2.point.Point;

class ShipImplTest {

    @Test
    void shouldCreateBarcaRandomPoints() throws MalformedException, DirectionException, PointException {
        Ship ship = new ShipImpl.Builder(ShipClass.BARCA).points()
                .build();
        assertEquals(1, ship.getPoints()
                .size());
    }

    @Test
    void shouldCreateBarcaWithGivenPoints() throws MalformedException, DirectionException, PointException {
        Point[] points = { new Point.Builder("G6").build() };
        Ship ship = new ShipImpl.Builder(ShipClass.BARCA).points(new Point.Builder(5, 6).build(), Direction.UP)
                .build();
        assertThat(ship.getPoints(), containsInAnyOrder(points));
    }

    @Test
    void upPointsSubmarineCreated() throws MalformedException, DirectionException, PointException {
        Point[] points = { new Point.Builder(5, 6).build(), new Point.Builder(4, 6).build(), new Point.Builder(3, 6).build() };
        Ship ship = new ShipImpl.Builder(ShipClass.SUBMARINE).points(new Point.Builder("G6").build(), Direction.UP).build();
        assertThat(ship.getPoints(), containsInAnyOrder(points));
    }
    @Test
    void bottomPointsCarrierCreated() throws MalformedException, DirectionException, PointException {
        Point[] points = { new Point.Builder(5, 6).build(), new Point.Builder(6, 6).build(), new Point.Builder(7, 6).build(), new Point.Builder(8, 6).build(),
                new Point.Builder(9, 6).build() };
        Ship ship = new ShipImpl.Builder(ShipClass.CARRIER).points(new Point.Builder("G6").build(), Direction.DOWN).build();
        assertThat(ship.getPoints(), containsInAnyOrder(points));
    }

}

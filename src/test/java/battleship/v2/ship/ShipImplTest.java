package battleship.v2.ship;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import battleship.v2.direction.Direction;
import battleship.v2.exception.DirectionException;
import battleship.v2.exception.MalformedException;
import battleship.v2.exception.PointException;
import battleship.v2.point.Point;

class ShipImplTest {
    private Ship baseShip;
    private Ship destroyer;

    @BeforeEach
    public void setup() throws MalformedException, DirectionException, PointException {
        this.baseShip = new ShipImpl.Builder(ShipClass.CARRIER).points(new Point.Builder("G6").build(), Direction.DOWN)
                .build();
        this.destroyer = new ShipImpl.Builder(ShipClass.DESTROYER)
                .points(new Point.Builder(3, 3).build(), Direction.RIGHT)
                .build();
    }

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
        Point[] points = {
                new Point.Builder(5, 6).build(),
                new Point.Builder(4, 6).build(),
                new Point.Builder(3, 6).build() };
        Ship ship = new ShipImpl.Builder(ShipClass.SUBMARINE).points(new Point.Builder("G6").build(), Direction.UP)
                .build();
        assertThat(ship.getPoints(), containsInAnyOrder(points));
    }

    @Test
    void bottomPointsCarrierCreated() throws MalformedException, DirectionException, PointException {
        Point[] points = {
                new Point.Builder(5, 6).build(),
                new Point.Builder(6, 6).build(),
                new Point.Builder(7, 6).build(),
                new Point.Builder(8, 6).build(),
                new Point.Builder(9, 6).build() };
        assertThat(baseShip.getPoints(), containsInAnyOrder(points));
    }

    @Test
    void shotReturnFalseWhenShotPointNotInPoints() throws MalformedException, DirectionException, PointException {

        assertFalse(baseShip.shoot(new Point.Builder("a1").build()));
    }

    @Test
    void shotReturnTrueWhenShotPointInPoints() throws MalformedException, DirectionException, PointException {

        assertTrue(baseShip.shoot(new Point.Builder("G6").build()));
    }

    @Test
    void whenShotAndPointInPointsHealthIsSmaller() throws MalformedException, DirectionException, PointException {

        baseShip.shoot(new Point.Builder("G6").build());
        assertEquals(4, baseShip.getHealth());
    }

    @Test
    void when2ShotsAndPointInPointsHealthIsSmaller() throws MalformedException, DirectionException, PointException {
        baseShip.shoot(new Point.Builder("G6").build());
        baseShip.shoot(new Point.Builder("G7").build());
        assertEquals(3, baseShip.getHealth());
    }

    @Test
    void shouldBeSunkAfterShots() throws MalformedException, DirectionException, PointException {
        baseShip.shoot(new Point.Builder("G6").build());
        baseShip.shoot(new Point.Builder("G7").build());
        baseShip.shoot(new Point.Builder("G8").build());
        baseShip.shoot(new Point.Builder("G9").build());
        baseShip.shoot(new Point.Builder("G10").build());
        assertTrue(baseShip.isSunk());
    }

    @Test
    void otherShipIsUpAndToCloseReturnTrue() throws MalformedException, DirectionException, PointException {
        Ship toCloseShip = new ShipImpl.Builder(ShipClass.DESTROYER)
                .points(new Point.Builder(2, 3).build(), Direction.RIGHT)
                .build();
        assertTrue(destroyer.toCloseTo(toCloseShip));
    }
    @Test
    void otherShipIsDownAndToCloseReturnTrue() throws MalformedException, DirectionException, PointException {
        Ship toCloseShip = new ShipImpl.Builder(ShipClass.DESTROYER)
                .points(new Point.Builder(4, 3).build(), Direction.DOWN)
                .build();
        assertTrue(destroyer.toCloseTo(toCloseShip));
    }

}

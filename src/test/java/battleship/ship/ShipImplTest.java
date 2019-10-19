package battleship.ship;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.direction.Direction;
import battleship.exception.MalformattedException;
import battleship.point.Point;

class ShipImplTest {
    private Ship baseShip;
    private Ship destroyer;

    @BeforeEach
    public void setup() throws MalformattedException {
        this.baseShip = new ShipImpl.Builder(ShipClass.CARRIER).points(new Point.Builder("G6").build(), Direction.DOWN)
                .build();
        this.destroyer = new ShipImpl.Builder(ShipClass.DESTROYER)
                .points(new Point.Builder(3, 3).build(), Direction.RIGHT)
                .build();
    }

    @Test
    void shouldCreateBarcaRandomPoints() throws MalformattedException {
        Ship ship = new ShipImpl.Builder(ShipClass.BARCA).points()
                .build();
        assertEquals(1, ship.getPoints()
                .size());
    }

    @Test
    void shouldCreateBarcaWithGivenPoints() throws MalformattedException {
        Point[] points = {new Point.Builder("G6").build()};
        Ship ship = new ShipImpl.Builder(ShipClass.BARCA).points(new Point.Builder(5, 6).build(), Direction.UP)
                .build();
        assertThat(ship.getPoints(), containsInAnyOrder(points));
    }

    @Test
    void upPointsSubmarineCreated() throws MalformattedException {
        Point[] points = {
                new Point.Builder(5, 6).build(),
                new Point.Builder(4, 6).build(),
                new Point.Builder(3, 6).build()};
        Ship ship = new ShipImpl.Builder(ShipClass.SUBMARINE).points(new Point.Builder("G6").build(), Direction.UP)
                .build();
        assertThat(ship.getPoints(), containsInAnyOrder(points));
    }

    @Test
    void bottomPointsCarrierCreated() throws MalformattedException{
        Point[] points = {
                new Point.Builder(5, 6).build(),
                new Point.Builder(6, 6).build(),
                new Point.Builder(7, 6).build(),
                new Point.Builder(8, 6).build(),
                new Point.Builder(9, 6).build()};
        assertThat(baseShip.getPoints(), containsInAnyOrder(points));
    }

    @Test
    void shotReturnFalseWhenNotIsAt() throws MalformattedException {

        assertFalse(baseShip.isAt(new Point.Builder("a1").build()).isPresent());
    }

    @Test
    void shotReturnTrueWhenShipIsAtPoint() throws MalformattedException{

        assertTrue(baseShip.isAt(new Point.Builder("G6").build()).isPresent());
    }

    @Test
    void whenShotAndPointInPointsHealthIsSmaller() throws MalformattedException {

        baseShip.shoot();
        assertEquals(4, baseShip.getHealth());
    }

    @Test
    void when2ShotsAndPointInPointsHealthIsSmaller() throws MalformattedException {
        baseShip.shoot();
        baseShip.shoot();
        assertEquals(3, baseShip.getHealth());
    }

    @Test
    void shouldBeSunkAfterShots() throws MalformattedException {
        baseShip.shoot();
        baseShip.shoot();
        baseShip.shoot();
        baseShip.shoot();
        baseShip.shoot();
        assertTrue(baseShip.isSunk());
    }
    @Test
    public void testTooManyHits() {
        destroyer.shoot();
        destroyer.shoot();
        destroyer.shoot();
        
        assertThrows(IllegalStateException.class, ()->destroyer.shoot());
    }

    @Test
    void otherShipIsUpAndToCloseReturnTrue() throws MalformattedException{
        Ship toCloseShip = new ShipImpl.Builder(ShipClass.DESTROYER)
                .points(new Point.Builder(2, 3).build(), Direction.RIGHT)
                .build();
        assertTrue(destroyer.toCloseTo(toCloseShip));
    }

    @Test
    void otherShipIsDownAndToCloseReturnTrue() throws MalformattedException{
        Ship toCloseShip = new ShipImpl.Builder(ShipClass.DESTROYER)
                .points(new Point.Builder(4, 3).build(), Direction.DOWN)
                .build();
        assertTrue(destroyer.toCloseTo(toCloseShip));
    }
    @Test
    void getMostTopPosition() {
        assertThat(destroyer.getMostTopPosition(), is(3));
    }

    @Test
    void getMostBottomPosition() {
        assertThat(destroyer.getMostBottomPosition(), is(3));
    }

    @Test
    void getMostLeftPosition() {
        assertThat(destroyer.getMostLeftPosition(), is(3));
    }
    @Test
    void getMostRightPosition() {
        assertThat(destroyer.getMostRightPosition(), is(5));
    }

}

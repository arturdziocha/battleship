package battleship.ship;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.direction.Direction;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import battleship.ship.ShipImpl;
import io.vavr.control.Either;

class ShipImplTest {
    private Ship baseShip;
    private Ship destroyer;

    @BeforeEach
    void setup() {
        this.baseShip = new ShipImpl.Builder(ShipClass.CARRIER, new PointImpl.Builder("G6").build()
                .get(), Direction.DOWN).build()
                .get();
        this.destroyer = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(3, 3).build()
                .get(), Direction.RIGHT).build()
                .get();
    }

    @Test
    @DisplayName("Should create Random points of Ship")
    void shouldCreateBarcaRandomPoints() {
        Either<String, Ship> ship = new ShipImpl.Builder(ShipClass.BARCA).build();
        assertEquals(1, ship.get()
                .getPoints()
                .size());
    }

    @Test
    @DisplayName("Should create Ship Barca with given start point")
    void shouldCreateBarcaWithGivenPoints() {
        Point[] points = {new PointImpl.Builder("G6").build().get()};
        Ship ship = new ShipImpl.Builder(ShipClass.BARCA, new PointImpl.Builder(5, 6).build().get(), Direction.UP)
                .build().get();
        assertThat(ship.getPoints(), containsInAnyOrder(points));
    }

    @Test
    @DisplayName("When instantiated with start point and direction then contains points")
    void upPointsSubmarineCreated() {
        Point[] points = {
                new PointImpl.Builder(5, 6).build().get(),
                new PointImpl.Builder(4, 6).build().get(),
                new PointImpl.Builder(3, 6).build().get()};
        Ship ship = new ShipImpl.Builder(ShipClass.SUBMARINE, new PointImpl.Builder("G6").build().get(), Direction.UP)
                .build().get();
        assertThat(ship.getPoints(), containsInAnyOrder(points));
    }

    @Test
    @DisplayName("When instantiated with start point and direction points are created")
    void bottomPointsCarrierCreated() {
        Point[] points = {
                new PointImpl.Builder(5, 6).build().get(),
                new PointImpl.Builder(6, 6).build().get(),
                new PointImpl.Builder(7, 6).build().get(),
                new PointImpl.Builder(8, 6).build().get(),
                new PointImpl.Builder(9, 6).build().get()};
        assertThat(baseShip.getPoints(), containsInAnyOrder(points));
    }

    @Test
    @DisplayName("When shot and Ship don't have point return Option.none")
    void shotReturnOptionNoneWhenNotIsAt() {
        assertEquals("None", baseShip.isAt(new PointImpl.Builder("a1").build().get()).toString());
    }

    @Test
    @DisplayName("When shot and Ship  have point return Option.Some")
    void shotReturnOptionSomeWhenIsAt() {
        assertTrue(baseShip.isAt(new PointImpl.Builder("g6").build().get()).isDefined());
    }

    @Test
    @DisplayName("When shot health is smaller")
    void whenShotAndPointInPointsHealthIsSmaller() {
        baseShip.shoot();
        assertEquals(4, baseShip.getHealth());
    }

    @Test
    @DisplayName("When 2 shot health is smaller")
    void when2ShotsAndPointInPointsHealthIsSmaller() {
        baseShip.shoot();
        baseShip.shoot();
        assertEquals(3, baseShip.getHealth());
    }

    @Test
    @DisplayName("When 5 shots then ship is sunk")
    void shouldBeSunkAfterShots() {
        baseShip.shoot();
        baseShip.shoot();
        baseShip.shoot();
        baseShip.shoot();
        baseShip.shoot();
        assertTrue(baseShip.isSunk());
    }

    @Test
    @DisplayName("When to many shots ship is sunk and return false")
    void testTooManyHits() {
        destroyer.shoot();
        destroyer.shoot();
        destroyer.shoot();

        assertFalse(destroyer.shoot());
    }

    @Test
    @DisplayName("When ship is up and to close another ship then return true")
    void otherShipIsUpAndToCloseReturnTrue() {
        Ship toCloseShip = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(2, 3).build().get(), Direction.RIGHT).build().get();
        assertTrue(destroyer.toCloseTo(toCloseShip));
    }

    @Test
    @DisplayName("When ship is down and to close another ship then return true")
    void otherShipIsDownAndToCloseReturnTrue() {
        Ship toCloseShip = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(4, 3).build().get(), Direction.DOWN).build().get();
        assertTrue(destroyer.toCloseTo(toCloseShip));
    }

    @Test
    @DisplayName("Get most top position of ship")
    void getMostTopPosition() {
        assertEquals(new PointImpl.Builder(3, 3).build().get(), destroyer.getMostTopPosition().get());
    }

    @Test
    @DisplayName("Get most bottom position of ship")
    void getMostBottomPosition() {
        assertEquals(new PointImpl.Builder(3, 3).build().get(), destroyer.getMostBottomPosition().get());
    }

    @Test
    @DisplayName("Get most left position of ship")
    void getMostLeftPosition() {
        assertEquals(new PointImpl.Builder(3, 3).build().get(), destroyer.getMostLeftPosition().get());
    }

    @Test
    @DisplayName("Get most right position of ship")
    void getMostRightPosition() {
        assertEquals(new PointImpl.Builder(3, 5).build().get(), destroyer.getMostRightPosition().get());
    }

}

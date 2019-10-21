package battleship.fleet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.direction.Direction;
import battleship.exception.MalformattedException;
import battleship.exception.ShipOverlapException;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import battleship.ship.ShipImpl;

class FleetImplTest {
    private Fleet fleet;

    @BeforeEach
    void setUp() {
        fleet = new FleetImpl.Builder().build();
    }

    @Test
    void shouldThrowIllegalArgExceWhenPlaceNullShip() {
        assertThrows(IllegalArgumentException.class, () -> fleet.placeShip(null));
    }

    @Test
    void shouldReturn1When1ShipPlaced() throws MalformattedException, ShipOverlapException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(7, 7).build(), Direction.DOWN)
                .build();
        fleet.placeShip(ship);
        assertEquals(1, fleet.getShips().size());
    }

    @Test
    void shouldThrowsShiOutsideBoardWhenShipPointBottomOutsideBoard()
            throws MalformattedException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(8, 7).build(), Direction.DOWN)
                .build();
        assertThrows(ShipOverlapException.class, () -> fleet.placeShip(ship));
    }

    @Test
    void shouldThrowsShiOutsideBoardWhenShipPointTopOutsideBoard()
            throws MalformattedException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(1, 7).build(), Direction.UP)
                .build();
        assertThrows(ShipOverlapException.class, () -> fleet.placeShip(ship));
    }

    @Test
    void shouldThrowsShiOutsideBoardWhenShipPointRigthOutsideBoard()
            throws MalformattedException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(1, 8).build(), Direction.RIGHT)
                .build();
        assertThrows(ShipOverlapException.class, () -> fleet.placeShip(ship));
    }

    @Test
    void shouldThrowsShiOutsideBoardExceptionWhenShipPointLeftOutsideBoard()
            throws MalformattedException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(1, 1).build(), Direction.LEFT)
                .build();
        assertThrows(ShipOverlapException.class, () -> fleet.placeShip(ship));
    }

    @Test
    void shouldPutShipInFleetWhenShipIsNotToCloseShips() throws MalformattedException, ShipOverlapException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(1, 1).build(), Direction.RIGHT)
                .build();
        Ship notToClose = new ShipImpl.Builder(ShipClass.SUBMARINE)
                .points(new PointImpl.Builder(3, 1).build(), Direction.RIGHT)
                .build();
        fleet.placeShip(ship);
        fleet.placeShip(notToClose);
        assertEquals(2, fleet.getShips().size());
    }

    @Test
    void shouldThrowShipToCloseExceptionWhenShipIsToCloseShips() throws MalformattedException, ShipOverlapException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(1, 1).build(), Direction.RIGHT)
                .build();
        Ship toClose = new ShipImpl.Builder(ShipClass.SUBMARINE)
                .points(new PointImpl.Builder(2, 1).build(), Direction.RIGHT)
                .build();
        fleet.placeShip(ship);
        assertThrows(ShipOverlapException.class, () -> fleet.placeShip(toClose));
    }

    @Test
    void shouldReturnFiveShipsNotPlacedYet() throws MalformattedException, ShipOverlapException {
        ShipClass[] shipClassesNotPlaced = {ShipClass.BARCA, ShipClass.BATTLESHIP, ShipClass.CARRIER, ShipClass.PATROL_BOAT, ShipClass.SUBMARINE};
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(1, 1).build(), Direction.RIGHT)
                .build();
        fleet.placeShip(ship);
        System.out.println(fleet.shipsToPlace());
        assertThat(fleet.shipsToPlace(), containsInAnyOrder(shipClassesNotPlaced));

    }

    @Test
    void shouldReturnTrueWhenAllShipsPlaced() throws MalformattedException, ShipOverlapException {
        Ship[] ships = {
                new ShipImpl.Builder(ShipClass.BARCA).points(new PointImpl.Builder(0, 0).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.PATROL_BOAT).points(new PointImpl.Builder(0, 2).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.SUBMARINE).points(new PointImpl.Builder(0, 4).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(0, 6).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.BATTLESHIP).points(new PointImpl.Builder(0, 8).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.CARRIER).points(new PointImpl.Builder(7, 0).build(), Direction.RIGHT)
                        .build()};
        for (Ship ship : ships) {
            fleet.placeShip(ship);
        }
        assertTrue(fleet.isAllShipsPlaced());
    }

    @Test
    void shouldReturnFalseWhenAllShipsPlaced() throws MalformattedException, ShipOverlapException {
        Ship[] ships = {
                new ShipImpl.Builder(ShipClass.BARCA).points(new PointImpl.Builder(0, 0).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.PATROL_BOAT).points(new PointImpl.Builder(0, 2).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.SUBMARINE).points(new PointImpl.Builder(0, 4).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(0, 6).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.BATTLESHIP).points(new PointImpl.Builder(0, 8).build(), Direction.DOWN)
                        .build()};
        for (Ship ship : ships) {
            fleet.placeShip(ship);
        }
        assertFalse(fleet.isAllShipsPlaced());
    }

    @Test
    void getAtAndShipExistsAtPointReturnOptionalOfShip() throws MalformattedException, ShipOverlapException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(7, 4).build(), Direction.DOWN)
                .build();
        Point point = new PointImpl.Builder(8, 4).build();
        fleet.placeShip(ship);
        assertTrue(fleet.shipAt(point)
                .isPresent());
        assertEquals(ship, fleet.shipAt(point)
                .get());
    }

    @Test
    void shouldReturnOptionalOfShipWhenGetAtAndShipNotExistsAtPoint()
            throws MalformattedException, ShipOverlapException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new PointImpl.Builder(7, 4).build(), Direction.DOWN)
                .build();
        Point point = new PointImpl.Builder(1, 4).build();
        fleet.placeShip(ship);
        assertFalse(fleet.shipAt(point)
                .isPresent());
    }

    @Test
    void shouldReturnTrueWhenPlaceAllShipsRandom() throws MalformattedException, ShipOverlapException {
        fleet.placeShipsRandom();
        assertTrue(fleet.isAllShipsPlaced());
    }


}

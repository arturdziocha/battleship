package battleship.v2.fleet;

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
import battleship.v2.ship.Ship;
import battleship.v2.ship.ShipClass;
import battleship.v2.ship.ShipImpl;

class FleetImplTest {
    private Fleet fleet;

    @BeforeEach
    void setUp() {
        fleet = new FleetImpl();
    }

    @Test
    void placeShipReturnTrueWhenShipPointsInsideBoard() throws MalformedException, DirectionException, PointException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new Point.Builder(7, 7).build(), Direction.DOWN)
                .build();
        assertTrue(fleet.placeShip(ship));
    }

    @Test
    void placeShipReturnFalseWhenShipPointBottomOutsideBoard()
            throws MalformedException, DirectionException, PointException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new Point.Builder(8, 7).build(), Direction.DOWN)
                .build();
        assertFalse(fleet.placeShip(ship));
    }

    @Test
    void placeShipReturnFalseWhenShipPointTopOutsideBoard()
            throws MalformedException, DirectionException, PointException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new Point.Builder(1, 7).build(), Direction.UP)
                .build();
        assertFalse(fleet.placeShip(ship));
    }

    @Test
    void placeShipReturnFalseWhenShipPointRigthOutsideBoard()
            throws MalformedException, DirectionException, PointException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new Point.Builder(1, 8).build(), Direction.RIGHT)
                .build();
        assertFalse(fleet.placeShip(ship));
    }

    @Test
    void placeShipReturnFalseWhenShipPointLeftOutsideBoard()
            throws MalformedException, DirectionException, PointException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new Point.Builder(1, 1).build(), Direction.LEFT)
                .build();
        assertFalse(fleet.placeShip(ship));
    }

    @Test
    void shouldReturnTrueWhenShipIsNotToCloseShips() throws MalformedException, DirectionException, PointException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new Point.Builder(1, 1).build(), Direction.RIGHT)
                .build();
        Ship notToClose = new ShipImpl.Builder(ShipClass.SUBMARINE)
                .points(new Point.Builder(3, 1).build(), Direction.RIGHT)
                .build();
        assertTrue(fleet.placeShip(ship));
        assertTrue(fleet.placeShip(notToClose));
    }

    @Test
    void shouldReturnFalseWhenShipIsToCloseShips() throws MalformedException, DirectionException, PointException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new Point.Builder(1, 1).build(), Direction.RIGHT)
                .build();
        Ship notToClose = new ShipImpl.Builder(ShipClass.SUBMARINE)
                .points(new Point.Builder(2, 1).build(), Direction.RIGHT)
                .build();
        assertTrue(fleet.placeShip(ship));
        assertFalse(fleet.placeShip(notToClose));
    }

    @Test
    void shouldReturnTrueWhenAllShipsPlaced() throws MalformedException, DirectionException, PointException {
        Ship[] ships = {
                new ShipImpl.Builder(ShipClass.BARCA).points(new Point.Builder(0, 0).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.PATROL_BOAT).points(new Point.Builder(0, 2).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.SUBMARINE).points(new Point.Builder(0, 4).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.DESTROYER).points(new Point.Builder(0, 6).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.BATTLESHIP).points(new Point.Builder(0, 8).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.CARRIER).points(new Point.Builder(7, 0).build(), Direction.RIGHT)
                        .build() };
        for (Ship ship : ships) {
            fleet.placeShip(ship);
        }
        assertTrue(fleet.isAllShipsPlaced());
    }

    @Test
    void shouldReturnFalseWhenAllShipsPlaced() throws MalformedException, DirectionException, PointException {
        Ship[] ships = {
                new ShipImpl.Builder(ShipClass.BARCA).points(new Point.Builder(0, 0).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.PATROL_BOAT).points(new Point.Builder(0, 2).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.SUBMARINE).points(new Point.Builder(0, 4).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.DESTROYER).points(new Point.Builder(0, 6).build(), Direction.DOWN)
                        .build(),
                new ShipImpl.Builder(ShipClass.BATTLESHIP).points(new Point.Builder(0, 8).build(), Direction.DOWN)
                        .build() };
        for (Ship ship : ships) {
            fleet.placeShip(ship);
        }
        assertFalse(fleet.isAllShipsPlaced());
    }

    @Test
    void getAtAndShipExistsAtPointReturnOptionalOfShip() throws MalformedException, DirectionException, PointException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new Point.Builder(7, 4).build(), Direction.DOWN)
                .build();
        Point point = new Point.Builder(8, 4).build();
        fleet.placeShip(ship);
        assertTrue(fleet.shipAt(point)
                .isPresent());
        assertEquals(ship, fleet.shipAt(point)
                .get());
    }

    @Test
    void shouldReturnOptionalOfShipWhenGetAtAndShipNotExistsAtPoint()
            throws MalformedException, DirectionException, PointException {
        Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER).points(new Point.Builder(7, 4).build(), Direction.DOWN)
                .build();
        Point point = new Point.Builder(1, 4).build();
        fleet.placeShip(ship);
        fleet.placeShip(ship);
        assertFalse(fleet.shipAt(point)
                .isPresent());
    }
    @Test
    void shouldReturnTrueWhenPlaceAllShipsRandom() throws MalformedException, DirectionException, PointException {
        fleet.placeShipsRandom();
        assertTrue(fleet.isAllShipsPlaced());
    }
}

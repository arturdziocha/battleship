package battleship.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.direction.Direction;
import battleship.exception.MalformattedException;
import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.point.PointStatus;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import battleship.ship.ShipImpl;

/**
 * @author P
 *
 */
class ConsolePlayerTest {

    private Player player;

    private Fleet fleet;
    private Map<Point, PointStatus> expected;

    @BeforeEach
    void setUp() {
        expected = new TreeMap<>();
        fleet = mock(Fleet.class);
        this.player = new ConsolePlayer.Builder("artur", fleet).build();

    }

    @Test
    @DisplayName("When initialized Fleet Ships is empty")
    void whenInitializedFleetShipsIsEmpty() {
        when(fleet.getShips()).thenReturn(Collections.emptyList());
        assertEquals(0, player.getFleet()
                .getShips()
                .size());
    }

    @Test
    @DisplayName("Test getShots should return expected array of PointStatus.EMPTY")
    void shouldReturnArrayOfPointStatusEmpty() {
        assertTrue(expected.equals(player.getShots()));
    }

    @Test
    @DisplayName("Set shot should set shot point to HIT")
    void shouldSetShotPointToHit() throws MalformattedException {
        expected.put(new PointImpl.Builder(1, 1).build(), PointStatus.HIT);
        player.setShot(new PointImpl.Builder("b2").build(), PointStatus.HIT);
        assertTrue(expected.equals(player.getShots()));
    }

    @Test
    @DisplayName("Set shot sunk should set points SUNK and neigbors Occupied")
    void shouldSetPointsSunkWhenShipIsSunk() throws MalformattedException {

        // Occupied
        expected.put(new PointImpl.Builder(0, 0).build(), PointStatus.OCCUPIED);
        expected.put(new PointImpl.Builder(1, 0).build(), PointStatus.OCCUPIED);
        expected.put(new PointImpl.Builder(2, 0).build(), PointStatus.OCCUPIED);
        expected.put(new PointImpl.Builder(3, 0).build(), PointStatus.OCCUPIED);
        expected.put(new PointImpl.Builder(4, 0).build(), PointStatus.OCCUPIED);
        expected.put(new PointImpl.Builder(0, 1).build(), PointStatus.OCCUPIED);
        expected.put(new PointImpl.Builder(4, 1).build(), PointStatus.OCCUPIED);
        expected.put(new PointImpl.Builder(0, 2).build(), PointStatus.OCCUPIED);
        expected.put(new PointImpl.Builder(1, 2).build(), PointStatus.OCCUPIED);
        expected.put(new PointImpl.Builder(2, 2).build(), PointStatus.OCCUPIED);
        expected.put(new PointImpl.Builder(3, 2).build(), PointStatus.OCCUPIED);
        expected.put(new PointImpl.Builder(4, 2).build(), PointStatus.OCCUPIED);
        // SUNK
        expected.put(new PointImpl.Builder(1, 1).build(), PointStatus.SUNK);
        expected.put(new PointImpl.Builder(2, 1).build(), PointStatus.SUNK);
        expected.put(new PointImpl.Builder(3, 1).build(), PointStatus.SUNK);

        Ship ship = new ShipImpl.Builder(ShipClass.SUBMARINE)
                .points(new PointImpl.Builder("b2").build(), Direction.DOWN)
                .build();
        player.setShotSunk(ship);        
        assertTrue(expected.equals(player.getShots()));
    }

}

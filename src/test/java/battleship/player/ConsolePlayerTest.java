package battleship.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.direction.Direction;
import battleship.fleet.Fleet;
import battleship.player.AbstractPlayer;
import battleship.player.ConsolePlayer;
import battleship.player.Player;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.point.PointStatus;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import battleship.ship.ShipImpl;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.TreeMap;


class ConsolePlayerTest {
	private Player player;

    private Fleet fleet;
    private Map<Point, PointStatus> expected;

    @BeforeEach
    void setUp() {
        expected = TreeMap.empty(AbstractPlayer.POINT_COMPARATOR);
        fleet = mock(Fleet.class);
        this.player = new ConsolePlayer.Builder("artur", fleet).build();

    }

    @Test
    @DisplayName("When initialized Fleet Ships is empty")
    void whenInitializedFleetShipsIsEmpty() {
        when(fleet.getShips()).thenReturn(List.empty());
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
    void shouldSetShotPointToHit() {
        expected = expected.put(new PointImpl.Builder(1, 1).build().get(), PointStatus.HIT);
        player.setShot(new PointImpl.Builder("b2").build().get(), PointStatus.HIT);
        assertTrue(expected.equals(player.getShots()));
    }
    @Test
    @DisplayName("Shot in the same point should return true")
    void shotTheSamePointShouldReturnTrue() {        
        player.setShot(new PointImpl.Builder("b2").build().get(), PointStatus.HIT);
        assertTrue(player.isAlreadyShooted(new PointImpl.Builder("b2").build().get()));
    }
    @Test
    @DisplayName("Set shot sunk should set points SUNK and neigbors Occupied")
    void shouldSetPointsSunkWhenShipIsSunk() {

        // Occupied
        expected = expected.put(new PointImpl.Builder(0, 0).build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder(1, 0).build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder(2, 0).build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder(3, 0).build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder(4, 0).build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder(0, 1).build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder(4, 1).build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder(0, 2).build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder(1, 2).build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder(2, 2).build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder(3, 2).build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder(4, 2).build().get(), PointStatus.OCCUPIED);
        // SUNK
        expected = expected.put(new PointImpl.Builder(1, 1).build().get(), PointStatus.SUNK);
        expected = expected.put(new PointImpl.Builder(2, 1).build().get(), PointStatus.SUNK);
        expected = expected.put(new PointImpl.Builder(3, 1).build().get(), PointStatus.SUNK);
        
        Ship ship = new ShipImpl.Builder(ShipClass.SUBMARINE, new PointImpl.Builder("b2").build().get(), Direction.DOWN).build().get();
        player.setShotSunk(ship);        
        assertTrue(expected.equals(player.getShots()));
    }
    @Test
    @DisplayName("Set shot sunk 2 should set points SUNK and neigbors Occupied")
    void shouldSetPointsSunkWhenShipIsSunk2(){

        // Occupied
        expected = expected.put(new PointImpl.Builder("j4").build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder("i4").build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder("h4").build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder("g4").build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder("f4").build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder("e4").build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder("e5").build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder("e6").build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder("f6").build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder("g6").build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder("h6").build().get(), PointStatus.OCCUPIED);
        expected = expected.put(new PointImpl.Builder("i6").build().get(), PointStatus.OCCUPIED);        
        expected = expected.put(new PointImpl.Builder("j6").build().get(), PointStatus.OCCUPIED);
        // SUNK
        expected = expected.put(new PointImpl.Builder("f5").build().get(), PointStatus.SUNK);
        expected = expected.put(new PointImpl.Builder("G5").build().get(), PointStatus.SUNK);
        expected = expected.put(new PointImpl.Builder("H5").build().get(), PointStatus.SUNK);
        expected = expected.put(new PointImpl.Builder("I5").build().get(), PointStatus.SUNK);
        expected = expected.put(new PointImpl.Builder("J5").build().get(), PointStatus.SUNK);

        Ship ship = new ShipImpl.Builder(ShipClass.CARRIER,new PointImpl.Builder("f5").build().get(), Direction.RIGHT)
                .build().get();
        player.setShotSunk(ship);        
        assertTrue(expected.equals(player.getShots()));
    }

}

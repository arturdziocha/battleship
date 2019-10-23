package battleship.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import battleship.exception.MalformattedException;
import battleship.exception.NotAllShipsPlacedException;
import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.point.PointStatus;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import battleship.ship.ShipImpl;

class ConsolePlayerTest {

    private ConsolePlayer player;

    Fleet fleet;

    @BeforeEach
    void setUp() throws NotAllShipsPlacedException, MalformattedException {
        fleet = mock(Fleet.class);
        this.player = new ConsolePlayer.Builder("artur", fleet).build();

    }

    @Test
    @DisplayName("When initialized Fleet should be empty")
    void whenInitializedThenShipsToPlaceShouldReturnAllShipsToPlace() throws NotAllShipsPlacedException {
        doReturn(true).when(fleet)
                .isAllShipsPlaced();
        doReturn(Arrays.asList(ShipClass.values())).when(fleet)
                .shipsToPlace();

        ShipClass[] shipClasses = ShipClass.values();
        List<ShipClass> shipsToPlace = player.shipsToPlace();
        assertThat(shipsToPlace, containsInAnyOrder(shipClasses));
    }

    @Test
    @DisplayName("When shot to fleet then return optional of ship")
    void whenShotToFleeThenReturnOptionalOfShip() throws MalformattedException, NotAllShipsPlacedException {
        Ship ship = new ShipImpl.Builder(ShipClass.CARRIER).points()
                .build();
        Point point = new PointImpl.Builder("a2").build();

        doReturn(true).when(fleet)
                .isAllShipsPlaced();
        when(fleet.shipAt(point)).thenReturn(Optional.of(ship));

        assertEquals(ship, player.shootToFleet(point)
                .get());

    }

    @Test
    @DisplayName("When initialized Fleet Ships is empty")
    void whenInitializedFleetShipsIsEmpty() {
        when(fleet.getShips()).thenReturn(Arrays.asList());
        assertEquals(0, player.getFleet()
                .getShips()
                .size());
    }

    @Test
    @DisplayName("Test getShots should return expected array of PointStatus.EMPTY")
    void shouldReturnArrayOfPointStatusEmpty() {
        PointStatus[][] expected = new PointStatus[10][10];
        IntStream.range(0, 10)
                .forEach(x -> IntStream.range(0, 10)
                        .forEach(y -> expected[x][y] = PointStatus.EMPTY));
        assertArrayEquals(expected, player.getShots());

    }
    /**
    */
}

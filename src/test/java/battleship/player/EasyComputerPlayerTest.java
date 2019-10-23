package battleship.player;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.exception.MalformattedException;
import battleship.exception.ShipPlacementException;
import battleship.fleet.Fleet;

class EasyComputerPlayerTest {
    private Player player;
    private Fleet fleet;

    @BeforeEach
    void setUp() throws MalformattedException, ShipPlacementException {
        fleet = mock(Fleet.class);
        player = new EasyComputerPlayer.Builder(fleet).build();
    }

    @DisplayName("When Initialized Player should create fleet with ships")
    @Test
    void initializePlayerShouldCreateFleetWithShips() {
        doReturn(true).when(fleet).isAllShipsPlaced();
        assertTrue(player.getFleet().isAllShipsPlaced());
    }


}

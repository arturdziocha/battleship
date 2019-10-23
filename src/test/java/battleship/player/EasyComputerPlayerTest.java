package battleship.player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.exception.MalformattedException;
import battleship.exception.ShipOverlapException;
import battleship.fleet.Fleet;

class EasyComputerPlayerTest {
    private Player player;
    Fleet fleet;
    @BeforeEach
    void setUp() throws MalformattedException, ShipOverlapException{
        fleet = mock(Fleet.class);
        player = new EasyComputerPlayer.Builder().build();
    }
    /**
    @DisplayName("When Initialized Player should create fleet with ships")
    @Test
    void initializePlayerShouldCreateFleetWithShips() {
        assertTrue(player.getFleet().isAllShipsPlaced());
    }
    */

}

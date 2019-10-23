package battleship.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.exception.MalformattedException;
import battleship.exception.ShipOverlapException;

class HardComputerPlayerTest {
    private Player player;

    @BeforeEach
    void setUp() throws MalformattedException, ShipOverlapException {
        player = new HardComputerPlayer.Builder().build();
    }
/**
    @DisplayName("When Initialized Player should create fleet with ships")
    @Test
    void initializePlayerShouldCreateFleetWithShips() {
        assertTrue(player.getFleet()
                .isAllShipsPlaced());
    }
*/
}

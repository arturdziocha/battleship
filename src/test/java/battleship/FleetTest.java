package battleship;

import battleship.interfaces.Fleet;
import org.junit.jupiter.api.BeforeEach;

public class FleetTest {
    private Fleet fleet;
    @BeforeEach
    void setUp() {
        fleet = new FleetImpl();
    }

}

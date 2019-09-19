package battleship.cmdImpl;

import org.junit.jupiter.api.BeforeEach;

import battleship.Player;
import battleship.cmdImpl.CmdPlayerImpl;

public class PlayerImplTest {
	private Player player;
	@BeforeEach
	void setUp() {
		this.player = new CmdPlayerImpl("Artur");
	}

}

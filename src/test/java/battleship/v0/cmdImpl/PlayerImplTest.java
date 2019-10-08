package battleship.v0.cmdImpl;

import org.junit.jupiter.api.BeforeEach;

import battleship.Player;

public class PlayerImplTest {
	private Player player;
	@BeforeEach
	void setUp() {
		this.player = new CmdPlayerImpl("Artur");
	}

}

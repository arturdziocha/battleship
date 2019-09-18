package battleship;

import org.junit.jupiter.api.BeforeEach;

import battleship.cmdImpl.PlayerImpl;

public class PlayerTest {
	private Player player;
	@BeforeEach
	void setUp() {
		this.player = new PlayerImpl("Artur");
	}

}

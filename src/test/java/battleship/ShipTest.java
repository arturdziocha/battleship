package battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {
	private ShipClass shipClass;
	private Ship ship;

	@BeforeEach
	void setUp() throws Exception {
		this.shipClass = ShipClass.Submarine;
		this.ship = new Ship(shipClass);
	}

	@Test
	void whenInstantiatedThenShipClasIsStored() {
		assertEquals(shipClass, ship.getShipClass());
	}

	@Test
	void whenInstantiatedThenLivesIsStored() {
		assertEquals(3, ship.getLives());
	}

	@Test
	public void whenShotThenLivesDecreases() throws AlreadySunkEception {
		ship.shot();
		assertEquals(2, ship.getLives());
	}

	@Test
	public void whenShotAndSunkThenThrowsException() throws AlreadySunkEception {
		ship.shot();
		ship.shot();
		ship.shot();
		assertThrows(AlreadySunkEception.class, () -> ship.shot());
	}

}

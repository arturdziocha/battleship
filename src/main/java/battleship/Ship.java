package battleship;

public class Ship {
	private ShipClass shipClass;
	private int lives;

	public Ship(ShipClass shipClass) {
		this.shipClass = shipClass;
		this.lives = shipClass.getSize();
	}

	public ShipClass getShipClass() {
		return shipClass;
	}

	public int getLives() {
		return lives;
	}

	public void shot() throws AlreadySunkEception {
		if (isSunk()) {
			throw new AlreadySunkEception();
		}
		this.lives -= 1;
	}

	public boolean isSunk() {
		return this.lives == 0;
	}

}

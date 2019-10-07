package battleship.v1.ship;

public enum ShipClass {
	BARCA(1), BARCA1(1), PATROL_BOAT(2), SUBMARINE(3), DESTROYER(3), BATTLESHIP(4), CARRIER(5);
	private int size;

	private ShipClass(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
}

package battleship.ship;

public enum ShipClass {
	Barca(1), PatrolBoat(2), Submarine(3), Destroyer(3), Battleship(4), Carrier(5);
	private int size;

	private ShipClass(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
}

package battleship;

public enum ShipClass {
	Barca(1, 1), PatrolBoat(2, 2), Submarine(2, 3), Destroyer(4, 3), Battleship(5, 4), Carrier(6, 5);
	private int id;
	private int size;

	private ShipClass(int id, int size) {
		this.id = id;
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public int getSize() {
		return size;
	}
}

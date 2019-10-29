package battleship.ship;

public enum ShipClass {
    BARCA(1, "Barca"), PATROL_BOAT(2, "Patrol Boat"), SUBMARINE(3, "Submarine"), DESTROYER(3, "Destroyer"), BATTLESHIP(4, "Battleship"), CARRIER(5, "Carrier");
    private final int size;
    private final String name;

    ShipClass(final int size, String name) {
        this.size = size;
        this.name = name;
    }

    public int getSize() {
        return size;
    }
    public String getName() {
		return name;
	}

}

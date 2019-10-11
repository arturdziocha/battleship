package battleship.v2.ship;

public enum ShipClass {
    BARCA(1), PATROL_BOAT(2), SUBMARINE(3), DESTROYER(3), BATTLESHIP(4), CARRIER(5);
    private final int size;

    private ShipClass(final int size) {
        this.size = size;

    }

    public int getSize() {
        return size;
    }
}

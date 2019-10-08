package battleship.v2.ship;

import battleship.v2.MalformedException;

public enum Direction {
    UP(0, 'u'), RIGHT(1, 'r'), DOWN(2, 'd'), LEFT(3, 'l'), NONE(4, 'n');
    private final int value;
    private final char shortName;

    Direction(final int value, char shortName) {
        this.value = value;
        this.shortName = shortName;
    }

    public static Direction getFromShortName(char u) {
        for (Direction direction : Direction.values()) {
            if (direction.shortName == u) {
                return direction;
            }
        }
        return Direction.NONE;
    }

    public Direction turnLeft() {
        int index = (value + 3) % 4;
        return Direction.values()[index];
    }

    public Direction turnRight() {
        int index = (value + 1) % 4;
        return Direction.values()[index];
    }
}

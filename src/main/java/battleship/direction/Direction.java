package battleship.direction;

import java.util.Random;

import battleship.exception.MalformattedException;


public enum Direction {
    UP(0, 'u'), RIGHT(1, 'r'), DOWN(2, 'd'), LEFT(3, 'l');
    private final int value;
    private final char shortName;

    Direction(final int value, char shortName) {
        this.value = value;
        this.shortName = shortName;
    }

    public static Direction getFromShortName(char u) throws MalformattedException {
        for (Direction direction : Direction.values()) {
            if (direction.shortName == u) {
                return direction;
            }
        }
        throw new MalformattedException("Cannot find Direction");
    }

    public static Direction getRandomDirection() {
        Random random = new Random();
        return Direction.values()[random.nextInt(Direction.values().length)];
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

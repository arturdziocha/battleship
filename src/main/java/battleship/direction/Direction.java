package battleship.direction;

import java.util.Random;

import io.vavr.control.Either;

public enum Direction {
    UP(0, 'u'), RIGHT(1, 'r'), DOWN(2, 'd'), LEFT(3, 'l');
    private final int value;
    private final char shortName;

    Direction(final int value, final char shortName) {
        this.value = value;
        this.shortName = shortName;
    }

    public char getShortName() {
        return shortName;
    }

    public Direction turnLeft() {
        int index = (value + 3) % 4;
        return Direction.values()[index];
    }

    public Direction turnRight() {
        int index = (value + 1) % 4;
        return Direction.values()[index];
    }

    public static Either<String, Direction> getFromShortName(char u) {
        for (Direction direction : Direction.values()) {
            if (direction.shortName == u) {
                return Either.right(direction);
            }
        }
        return Either.left("Direction not found");

    }

    public static Direction getRandomDirection() {
            Random random = new Random();
            return Direction.values()[random.nextInt(Direction.values().length)];
        }
}

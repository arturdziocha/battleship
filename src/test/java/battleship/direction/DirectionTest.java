package battleship.direction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.direction.Direction;
import io.vavr.control.Either;

class DirectionTest {
    @Test
    @DisplayName("When given wrong Direction then return Either.left")
    void whenGivenWrongDirectionThenRetrunEitherLeft() {
        Either<String, Direction> direction = Direction.getFromShortName('w');
        assertTrue(direction.isLeft());
        assertEquals("Direction not found", direction.getLeft());
    }

    @Test
    public void whenGetFromShortNameUThenReturnDirectionUp() {
        Either<String, Direction> direction = Direction.getFromShortName('u');
        assertEquals(Direction.UP, direction.get());
    }

    @Test
    public void whenGetFromShortNameRThenReturnDirectionRight() {
        Either<String, Direction> direction = Direction.getFromShortName('r');
        assertEquals(Direction.RIGHT, direction.get());
    }

    @Test
    public void whenGetFromShortNameDThenReturnDirectionDown() {
        Either<String, Direction> direction = Direction.getFromShortName('d');
        assertEquals(Direction.DOWN, direction.get());
    }

    @Test
    public void whenGetFromShortNameLThenReturnDirectionLeft() {
        Either<String, Direction> direction = Direction.getFromShortName('l');
        assertEquals(Direction.LEFT, direction.get());
    }

    @Test
    public void givenDWhenLeftThenR() {
        assertEquals(Direction.DOWN.turnLeft(), Direction.RIGHT);
    }

    @Test
    public void givenNWhenLeftThenW() {
        assertEquals(Direction.UP.turnLeft(), Direction.LEFT);
    }

    @Test
    public void givenSWhenRightThenW() {
        assertEquals(Direction.DOWN.turnRight(), Direction.LEFT);
    }

    @Test
    public void givenWWhenRightThenN() {

        assertEquals(Direction.LEFT.turnRight(), Direction.UP);
    }
}

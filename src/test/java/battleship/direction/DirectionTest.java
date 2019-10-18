package battleship.direction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import battleship.exception.MalformattedException;

class DirectionTest {
    @Test
    public void whenGetFromShortNameUThenReturnDirectionUp() throws MalformattedException {
        Direction direction = Direction.getFromShortName('u');
        assertEquals(Direction.UP, direction);
    }

    @Test
    public void whenGetFromShortNameRThenReturnDirectionRight() throws MalformattedException {
        Direction direction = Direction.getFromShortName('r');
        assertEquals(Direction.RIGHT, direction);
    }

    @Test
    public void whenGetFromShortNameDThenReturnDirectionDown() throws MalformattedException {
        Direction direction = Direction.getFromShortName('d');
        assertEquals(Direction.DOWN, direction);
    }

    @Test
    public void whenGetFromShortNameLThenReturnDirectionLeft() throws MalformattedException {
        Direction direction = Direction.getFromShortName('l');
        assertEquals(Direction.LEFT, direction);
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
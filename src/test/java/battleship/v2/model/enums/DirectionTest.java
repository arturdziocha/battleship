package battleship.v2.model.enums;

import org.junit.jupiter.api.Test;

import battleship.v2.model.enums.Direction;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {
    @Test
    public void whenGetFromShortNameUThenReturnDirectionUp() {
        Direction direction = Direction.getFromShortName('u');
        assertEquals(Direction.UP, direction);
    }

    @Test
    public void whenGetFromShortNameRThenReturnDirectionRight() {
        Direction direction = Direction.getFromShortName('r');
        assertEquals(Direction.RIGHT, direction);
    }

    @Test
    public void whenGetFromShortNameDThenReturnDirectionDown() {
        Direction direction = Direction.getFromShortName('d');
        assertEquals(Direction.DOWN, direction);
    }

    @Test
    public void whenGetFromShortNameLThenReturnDirectionLeft() {
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
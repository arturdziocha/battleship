package battleship.direction;

import battleship.exception.MalformedException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {
    @Test
    public void whenGetFromShortNameUThenReturnDirectionUp() throws MalformedException {
        Direction direction = Direction.getFromShortName('u');
        assertEquals(Direction.UP, direction);
    }

    @Test
    public void whenGetFromShortNameRThenReturnDirectionRight() throws MalformedException {
        Direction direction = Direction.getFromShortName('r');
        assertEquals(Direction.RIGHT, direction);
    }

    @Test
    public void whenGetFromShortNameDThenReturnDirectionDown() throws MalformedException {
        Direction direction = Direction.getFromShortName('d');
        assertEquals(Direction.DOWN, direction);
    }

    @Test
    public void whenGetFromShortNameLThenReturnDirectionLeft() throws MalformedException {
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
package battleship.v1.point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import battleship.v1.MalformedException;

class PointDecoderTest {
    @Test
    void givenEmptyStringToInputToPointThrowsException() {
        assertThrows(MalformedException.class, ()->PointDecoder.inputToPoint(""));
    }
    @Test
    void givenPointIsTheSamePoint() throws MalformedException {
        Point point = new PointImpl(9, 9);
        Point pDecoded = PointDecoder.inputToPoint("J10");
        assertEquals(point, pDecoded);
    }

    @Test
    void givenAnotherPointIsFalse() throws MalformedException {
        Point point = new PointImpl(9, 9);
        Point pDecoded = PointDecoder.inputToPoint("d10");
        assertNotEquals(point, pDecoded);
    }

    @ParameterizedTest(name = "Is  the same point as input string: row = {0}, and column = {1}, Input String = {2}")
    @CsvSource({"0,0,A1", "2,3,D3", "6,8,I7", "5,8,I6", "4,8,I5", "4,7,H5", "4,6,G5", "5,6,G6", "9,9,J10"})
    void shouldReturnTrueWhenOtherIsTheSameFromInput(int x, int y, String input) throws MalformedException {
        Point point = new PointImpl(x, y);
        Point pointDecoded = PointDecoder.inputToPoint(input);
        assertEquals(point, pointDecoded);
    }

    @ParameterizedTest(name = "Is  not the same point as input string: row = {0}, and column = {1}, Input String = {2}")
    @CsvSource({"1,0,A1", "1,3,D3", "2,8,I7", "3,8,I6", "4,9,I5", "4,9,H5", "4,9,G5", "5,8,G6", "9,7,J10"})
    void shouldReturnFalseWhenOtherIsNotTheSameFromInput(int x, int y, String input) throws MalformedException {
        Point point = new PointImpl(x, y);
        Point pointDecoded = PointDecoder.inputToPoint(input);
        assertNotEquals(point, pointDecoded);
    }

    @Test
    void givenYOutsideBoardThrowsException() {
        assertThrows(MalformedException.class, () -> PointDecoder.inputToPoint("W9"));
    }

    @Test
    void givenXOutsideBoardThrowsException() {
        assertThrows(MalformedException.class, () -> PointDecoder.inputToPoint("a11"));
    }

    @Test
    void givenXYOutsideBoardThrowsException() {
        assertThrows(MalformedException.class, () -> PointDecoder.inputToPoint("H11"));
    }

    @Test
    void givenWrongInputReturnException() {
        assertThrows(MalformedException.class, () -> PointDecoder.inputToPoint("ab231"));
    }

    //Direction test
    @Test
    void givenEmptyStringToInputToDiectionThrowsException() {
        assertThrows(MalformedException.class, ()->PointDecoder.inputToDirection(""));
    }
    @Test
    void givenUppercaseStringShouldReturnDirection() throws MalformedException {
        Direction direction = Direction.LEFT;
        Direction directionDecoded = PointDecoder.inputToDirection("L");
        assertEquals(direction, directionDecoded);
    }
    @Test
    void givenDirectonInputLReturnDirectionLeft() throws MalformedException {
        assertEquals(Direction.LEFT, PointDecoder.inputToDirection("l"));
    }

    @Test
    void givenWrongDirectionReturnException() {
        assertThrows(IllegalArgumentException.class, () -> PointDecoder.inputToDirection("w"));
    }
}

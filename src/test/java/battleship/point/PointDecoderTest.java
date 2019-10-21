package battleship.point;

import battleship.exception.MalformattedException;
import battleship.point.PointImpl;
import battleship.point.PointDecoder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PointDecoderTest {
    @Test
    void shouldThrowsMalformattedExceptionWhenEmptyRowString() {
        assertThrows(MalformattedException.class, ()->PointDecoder.getColumn(""));
    }
    
    @Test
    void givenEmptyStringToInputToPointThrowsException() {
        assertThrows(MalformattedException.class, () -> PointDecoder.inputToPoint(""));
    }

    @Test
    void givenPointIsTheSamePoint() throws MalformattedException {
        PointImpl point = new PointImpl.Builder(9, 9).build();
        PointImpl pDecoded = PointDecoder.inputToPoint("J10");
        assertEquals(point, pDecoded);
    }

    @Test
    void givenAnotherPointIsFalse() throws MalformattedException {
        PointImpl point = new PointImpl.Builder(9, 9).build();
        PointImpl pDecoded = PointDecoder.inputToPoint("d10");
        assertNotEquals(point, pDecoded);
    }

    @ParameterizedTest(name = "Is  the same point as input string: row = {0}, and column = {1}, Input String = {2}")
    @CsvSource({ "0,0,A1", "2,3,D3", "6,8,I7", "5,8,I6", "4,8,I5", "4,7,H5", "4,6,G5", "5,6,G6", "9,9,J10" })
    void shouldEqualsWhenOtherIsTheSameFromInput(int x, int y, String input) throws MalformattedException {
        PointImpl point = new PointImpl.Builder(x, y).build();
        PointImpl pointDecoded = PointDecoder.inputToPoint(input);
        assertEquals(point, pointDecoded);
    }

    @ParameterizedTest(name = "Is  not the same point as input string: row = {0}, and column = {1}, Input String = {2}")
    @CsvSource({ "1,0,A1", "1,3,D3", "2,8,I7", "3,8,I6", "4,9,I5", "4,9,H5", "4,9,G5", "5,8,G6", "9,7,J10" })
    void shouldNotEqualsWhenOtherIsNotTheSameFromInput(int x, int y, String input) throws MalformattedException {
        PointImpl point = new PointImpl.Builder(x, y).build();
        PointImpl pointDecoded = PointDecoder.inputToPoint(input);
        assertNotEquals(point, pointDecoded);
    }

    @ParameterizedTest(name = "Is  the same X(row) when given String as row")
    @CsvSource({ "a1,0", "a2,1", "a3,2", "a4,3", "a5,4", "a6,5", "a7,6", "a8,7", "a9,8", "a10,9" })
    void shouldEqualsWhentherIsTheSameFromInput(String x, int y) throws MalformattedException {
        int row = PointDecoder.getRow(x);
        assertEquals(y, row);
    }
    @Test
    void shoultNotEqualsWhenStringIs2andExpected0() throws MalformattedException {
        int row = PointDecoder.getRow("a2");
        assertNotEquals(row, 0);
    }
    @ParameterizedTest(name = "Is  the same X(row) when given String as row")
    @CsvSource({ "a1,0", "b2,1", "c3,2", "D4,3", "E5,4", "F6,5", "g7,6", "h8,7", "i9,8", "J10,9" })
    void shouldEqualWhenIsTheSameStringFromInput(String x, int y) throws MalformattedException {
        int row = PointDecoder.getColumn(x);
        assertEquals(y, row);
    }
    @Test
    void shoultNotEqualsWhenStringIsCandExpected1() throws MalformattedException {
        int row = PointDecoder.getColumn("c2");
        assertNotEquals(row, 1);
    }
}
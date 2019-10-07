package battleship.v1.point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PointDecoderTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void givenPointIsTheSamePoint() {
        Point point = new PointImpl(9, 9);
        Point pDecoded = PointDecoder.inputToPoint("J10");
        assertEquals(point, pDecoded);
    }

    @Test
    void givenAnotherPointIsFalse() {
        Point point = new PointImpl(9, 9);
        Point pDecoded = PointDecoder.inputToPoint("d10");
        assertFalse(point.equals(pDecoded));
    }

    @ParameterizedTest(name = "Is neighbor return True when Other row = {0}, and column = {1}")
    @CsvSource({ "0,0,A1", "2,3,D3", "6,8,I7", "5,8,I6", "4,8,I5", "4,7,H5", "4,6,G5", "5,6,G6", "9,9,J10" })
    void shouldReturnTrueWhenOtherIsTheSameFromInput(int x, int y, String input) {
        Point point = new PointImpl(x, y);
        Point pointDecoded = PointDecoder.inputToPoint(input);
        assertTrue(point.equals(pointDecoded));
    }

    @ParameterizedTest(name = "Is neighbor return True when Other row = {0}, and column = {1}")
    @CsvSource({ "1,0,A1", "1,3,D3", "2,8,I7", "3,8,I6", "4,9,I5", "4,9,H5", "4,9,G5", "5,8,G6", "9,7,J10" })
    void shouldReturnFalseWhenOtherIsNotTheSameFromInput(int x, int y, String input) {
        Point point = new PointImpl(x, y);
        Point pointDecoded = PointDecoder.inputToPoint(input);
        assertFalse(point.equals(pointDecoded));
    }

    @Test
    void expectedExceptionWhenXOutsideBoard() {
        assertThrows(IllegalArgumentException.class, () -> PointDecoder.inputToPoint("W9"));
    }

    @Test
    void expectedExceptionWhenYOutsideBoard() {
        assertThrows(IllegalArgumentException.class, () -> PointDecoder.inputToPoint("A11"));
    }

    @Test
    void expectedExceptionWhenXYOutsideBoard() {
        assertThrows(IllegalArgumentException.class, () -> PointDecoder.inputToPoint("H11"));
    }
}

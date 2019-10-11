package battleship.v2.point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import battleship.v2.point.Point;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private int row = 5;
    private int column = 7;
    private Point point;

    @BeforeEach
    void setUp() {
        point = new Point.Builder(row, column).build();
    }

    @Test
    void whenInitializedRowIsSet() {
        assertEquals(row, point.getRow());
    }

    @Test
    void whenInitializedColumnIsSet() {
        assertEquals(column, point.getColumn());
    }

    @Test
    void isTheSameReturnTrueWhenThisSamePoint() {
        Point p = new Point.Builder(5, 7).build();
        assertEquals(point, p);
    }

    @Test
    void equalsReturnTrueWhenThisSamePoint() {
        Point p = new Point.Builder(5, 7).build();
        assertEquals(point, p);
    }

    @Test
    void equalsReturnFalseWhenOtherPoint() {
        Point p = new Point.Builder(5, 8).build();
        assertNotEquals(point, p);
    }

    @ParameterizedTest(name = "Is neighbor return True when Other row = {0}, and column = {1}")
    @CsvSource({ "6,6", "6,7", "6,8", "5,8", "4,8", "4,7", "4,6", "5,6" })
    void shouldReturnTrueWhenOtherIsNeighbor(int x, int y) {
        Point p = new Point.Builder(x, y).build();
        assertTrue(point.isNeighbor(p));
    }

    @ParameterizedTest(name = "Is neighbor return False when Other row = {0}, and column = {1}")
    @CsvSource({ "5,5", "3,6", "3,7", "3,8", "3,9", "4,9", "5,9", "6,9", "6,9" })
    void shouldReturnFalseWhenOtherIsNotNeighbor(int x, int y) {
        Point p = new Point.Builder(x, y).build();
        assertFalse(point.isNeighbor(p));
    }

}
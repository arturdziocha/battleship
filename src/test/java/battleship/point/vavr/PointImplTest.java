package battleship.point.vavr;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import io.vavr.control.Either;

class PointImplTest {
    private int row = 5;
    private int column = 7;
    private Either<String, Point> point;

    @BeforeEach
    void setUp() {
        point = new PointImpl.Builder(row, column).build();
    }

    @Test
    void whenInitializedRowIsSet() {
        assertEquals(row, point.get().getRow());
    }

    @Test
    void whenInitializedColumnIsSet() {
        assertEquals(column, point.get().getColumn());
    }
    @Test
    void isTheSameReturnTrueWhenThisSamePoint() {
        Either<String, Point> p = new PointImpl.Builder(5, 7).build();
        assertEquals(point.get(), p.get());
    }

    @Test
    void equalsReturnTrueWhenThisSamePoint() {
        Either<String, Point> p = new PointImpl.Builder(5, 7).build();
        assertEquals(point, p);
    }

    @Test
    void equalsReturnFalseWhenOtherPoint() {
        Either<String, Point> p = new PointImpl.Builder(5, 8).build();
        assertNotEquals(point, p);
    }

    @DisplayName("Is neighbor return True")
    @ParameterizedTest(name = "Is neighbor return True when Other row = {0}, and column = {1}")
    @CsvSource({ "6,6", "6,7", "6,8", "5,8", "4,8", "4,7", "4,6", "5,6" })
    void shouldReturnTrueWhenOtherIsNeighbor(int x, int y) {
        Either<String, Point> p = new PointImpl.Builder(x, y).build();
        assertTrue(point.get().isNeighbor(p.get()));
    }

    @ParameterizedTest(name = "Is neighbor return False when Other row = {0}, and column = {1}")
    @CsvSource({ "5,5", "3,6", "3,7", "3,8", "3,9", "4,9", "5,9", "6,9", "6,9" })
    void shouldReturnFalseWhenOtherIsNotNeighbor(int x, int y) {
        Either<String, Point> p = new PointImpl.Builder(x, y).build();
        assertFalse(point.get().isNeighbor(p.get()));
    }

    @Test
    @DisplayName("calculateNeigbors should return points neigbor points")
    void calculateNeighborsShouldReturnNeighborPoints() {
        Point[] points = {
                new PointImpl.Builder(4, 6).build().get(),
                new PointImpl.Builder(4, 7).build().get(),
                new PointImpl.Builder(4, 8).build().get(),
                new PointImpl.Builder(6, 6).build().get(),
                new PointImpl.Builder(6, 7).build().get(),
                new PointImpl.Builder(6, 8).build().get(),
                new PointImpl.Builder(5, 6).build().get(),
                new PointImpl.Builder(5, 8).build().get()};        
        assertThat(point.get().calculateNeighbors(), containsInAnyOrder(points));

    }
}

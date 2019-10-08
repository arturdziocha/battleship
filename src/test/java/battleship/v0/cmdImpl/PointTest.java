package battleship.v0.cmdImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import battleship.v0.point.Point;

class PointTest {
	int row = 5;
	int column = 7;
	private PointImpl point;

	@BeforeEach
	void setUp() throws Exception {
		point = new PointImpl(row, column);
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
	void whenInitializedStatusIsSetToEmpty() {
		assertTrue(point.isEmpty());
	}

	/**
	 * @Test void whenOccupyIsOccupiedIsTrue() { point.occupy();
	 *       assertTrue(point.isOccupied()); }
	 * 
	 * @Test void whenOccupiedHitIsFalse() { point.occupy();
	 *       assertFalse(point.isHit()); }
	 * 
	 * @Test void whenHitIsOccupiedIsFalse() { point.hit();
	 *       assertFalse(point.isOccupied()); }
	 */

	@Test
	void whenHitIsHitISTrue() {
		point.hit();
		assertTrue(point.isHit());
	}

	@Test
	void IsTheSameReturnTrueWhenThisSamePoint() {
		Point p = new PointImpl(5, 7);
		assertTrue(point.equals(p));
	}

	@Test
	void equalsReturnTrueWhenThisSamePoint() {
		Point p = new PointImpl(5, 7);
		assertTrue(point.equals(p));
	}

	@Test
	void equalsReturnFalseWhenOtherPoint() {
		Point p = new PointImpl(5, 8);
		assertFalse(point.equals(p));
	}

	@ParameterizedTest(name = "Is neighbor return True when Other row = {0}, and column = {1}")
	@CsvSource({ "6,6", "6,7", "6,8", "5,8", "4,8", "4,7", "4,6", "5,6" })
	void shouldReturnTrueWhenOtherIsNeighbor(int x, int y) {
		Point p = new PointImpl(x, y);
		assertTrue(point.isNeighbor(p));
	}
	@ParameterizedTest(name = "Is neighbor return False when Other row = {0}, and column = {1}")
	@CsvSource({ "5,5", "3,6", "3,7", "3,8", "3,9", "4,9", "5,9", "6,9", "6,9" })
	void shouldReturnFalseWhenOtherIsNotNeighbor(int x, int y) {
		Point p = new PointImpl(x, y);
		assertFalse(point.isNeighbor(p));
	}

}

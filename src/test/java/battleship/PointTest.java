package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.interfaces.Point;

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

	@Test
	void whenOccupyIsOccupiedIsTrue() {
		point.occupy();
		assertTrue(point.isOccupied());
	}

	@Test
	void whenOccupiedHitIsFalse() {
		point.occupy();
		assertFalse(point.isHit());
	}

	@Test
	void whenHitIsOccupiedIsFalse() {
		point.hit();
		assertFalse(point.isOccupied());
	}

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

	@Test
	void isNeighborReturnTrueWhenOtherIs67() {
		Point p = new PointImpl(6, 7);
		assertTrue(point.isNeighbor(p));
	}

	@Test
	void isNeighborReturnTrueWhenOtherIs68() {
		Point p = new PointImpl(6, 8);
		assertTrue(point.isNeighbor(p));
	}

	@Test
	void isNeighborReturnTrueWhenOtherIs58() {
		Point p = new PointImpl(5, 8);
		assertTrue(point.isNeighbor(p));
	}

	@Test
	void isNeighborReturnTrueWhenOtherIs48() {
		Point p = new PointImpl(4, 8);
		assertTrue(point.isNeighbor(p));
	}

	@Test
	void isNeighborReturnTrueWhenOtherIs47() {
		Point p = new PointImpl(4, 7);
		assertTrue(point.isNeighbor(p));
	}

	@Test
	void isNeighborReturnTrueWhenOtherIs46() {
		Point p = new PointImpl(4, 6);
		assertTrue(point.isNeighbor(p));
	}

	@Test
	void isNeighborReturnTrueWhenOtherIs56() {
		Point p = new PointImpl(5, 6);
		assertTrue(point.isNeighbor(p));
	}

	@Test
	void isNeighborReturnTrueWhenOtherIs66() {
		Point p = new PointImpl(6, 6);
		assertTrue(point.isNeighbor(p));
	}

	@Test
	void isNeighborReturnFalseWhenOtherIsNotNeighbor() {
		Point p = new PointImpl(3, 6);
		assertFalse(point.isNeighbor(p));
	}

}
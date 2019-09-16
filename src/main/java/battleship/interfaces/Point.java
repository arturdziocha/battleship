package battleship.interfaces;

public interface Point {
	int getRow();

	int getColumn();

	boolean isNeighbor(Point other);

	boolean equals(Point other);

	boolean isOccupied();

	boolean isHit();

	void occupy();

	void hit();
}

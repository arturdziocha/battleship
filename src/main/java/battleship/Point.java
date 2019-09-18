package battleship;

public interface Point {
	int getRow();

	int getColumn();

	boolean isNeighbor(Point other);

	boolean equals(Object other);

	boolean isOccupied();

	boolean isHit();

	void occupy();

	void hit();
}

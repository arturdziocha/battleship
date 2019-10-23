package battleship.point;

public interface Point {
    int getRow();

    int getColumn();

    boolean isNeighbor(Point other);

}

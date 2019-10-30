package battleship.point;

import java.util.Set;

public interface Point{
    int getRow();

    int getColumn();

    boolean isNeighbor(Point other);
    
    Set<Point> calculateNeighbors();

    boolean isInsideBoard();

}

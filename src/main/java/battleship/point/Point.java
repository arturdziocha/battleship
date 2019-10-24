package battleship.point;

import java.util.List;

public interface Point{
    int getRow();

    int getColumn();

    boolean isNeighbor(Point other);
    
    List<Point> calculateNeighbors();

}

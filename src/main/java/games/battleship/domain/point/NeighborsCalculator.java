package games.battleship.domain.point;

import java.util.Comparator;

import battleship.point.Point;
import io.vavr.collection.Set;
import io.vavr.collection.TreeSet;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class NeighborsCalculator {
    private final Point point;    
    public Set<Point> calculate(){
        Set<Point> points = TreeSet.empty(Comparator.comparingInt(Point::getRow)
                .thenComparing(Point::getColumn));
        return points;
    }
}

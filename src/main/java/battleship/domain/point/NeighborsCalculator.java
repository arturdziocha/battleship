package battleship.domain.point;

import java.util.Comparator;

import battleship.point.Point;
import io.vavr.collection.Set;
import io.vavr.collection.TreeSet;

public class NeighborsCalculator {
    private final Point point;
    public NeighborsCalculator(Point point) {
        this.point = point;
    }
    public Set<Point> calculate(){
        Set<Point> points = TreeSet.empty(Comparator.comparingInt(Point::getRow)
                .thenComparing(Point::getColumn));
        return points;
    }
}

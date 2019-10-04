package battleship.v1.point;

import java.util.List;

public interface PointsResolver {
	List<Point> resolve() throws IllegalArgumentException;
}

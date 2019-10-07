package battleship.v1.point;

import battleship.v1.MalformedException;

import java.util.List;

public interface PointsResolver {
	List<Point> resolve() throws MalformedException;
}

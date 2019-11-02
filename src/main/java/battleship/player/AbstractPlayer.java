package battleship.player;

import java.util.Comparator;

import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.Ship;
import io.vavr.collection.HashSet;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.collection.TreeMap;

public abstract class AbstractPlayer implements Player {
	public final static Comparator<Point> POINT_COMPARATOR = (p1, p2) -> p1.compareTo(p2);
	private Map<Point, PointStatus> shots = TreeMap.empty(POINT_COMPARATOR);
	Fleet fleet;
	String name;

	@Override
	public boolean hasLost() {
		return fleet.isAllShipsSunk();
	}

	@Override
	public Map<Point, PointStatus> getShots() {
		return shots;
	}

	@Override
	public boolean isAlreadyShooted(Point point) {
		return shots.containsKey(point);
	}

	@Override
	public void setShot(Point point, PointStatus pointStatus) {
		if (!point.isOutsideBoard()) {
			if (shots.containsKey(point))
				shots = shots.replaceValue(point, pointStatus);
			else
				shots = shots.put(point, pointStatus);
		}
	}

	@Override
	public void setShotSunk(Ship ship) {
		java.util.Set<Point> occupiesPoints = new java.util.HashSet<Point>();
		ship.getPoints()
		        .forEach(point -> {
			        setShot(point, PointStatus.SUNK);
			        point.calculateNeighbors()
			                .forEach(neighbor -> {
				                occupiesPoints.add(neighbor);
			                });
		        });

		Set<Point> occupies = HashSet.ofAll(occupiesPoints);
		occupies = occupies.removeAll(ship.getPoints());
		occupies.forEach(p -> setShot(p, PointStatus.OCCUPIED));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Fleet getFleet() {
		return fleet;
	}
}

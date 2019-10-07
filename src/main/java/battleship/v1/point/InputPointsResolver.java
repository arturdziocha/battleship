package battleship.v1.point;

import java.util.List;

import battleship.ship.ShipClass;

public class InputPointsResolver implements PointsResolver {

    private final ShipClass shipClass;
    private final String pointString;
    private final String directionString;

    public InputPointsResolver(ShipClass shipClass, String pointString, String directionString) {
        this.shipClass = shipClass;
        this.pointString = pointString;
        this.directionString = directionString;
    }

    @Override
    public List<Point> resolve() throws IllegalArgumentException {
        Point startPoint = PointDecoder.inputToPoint(pointString);
        
        List<Point> points = new PointsSetter(shipClass.getSize(), startPoint, Direction.DOWN).set();

        return points;
    }
}

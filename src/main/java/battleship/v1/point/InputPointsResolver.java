package battleship.v1.point;

import java.util.List;

import battleship.ship.ShipClass;
import battleship.v1.MalformedException;

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
    public List<Point> resolve() throws MalformedException {
        Point startPoint = PointDecoder.inputToPoint(pointString);
        Direction direction = PointDecoder.inputToDirection(directionString);

        return new PointsSetter(shipClass.getSize(), startPoint, direction).set();
    }
}

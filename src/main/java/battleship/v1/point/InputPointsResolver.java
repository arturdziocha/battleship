package battleship.v1.point;

import java.util.List;

import battleship.v1.MalformedException;
import battleship.v1.ship.ShipClass;

public class InputPointsResolver implements PointsResolver {

    private final ShipClass shipClass;
    private final Point startPoint;
    private final Direction direction;

    public InputPointsResolver(ShipClass shipClass, String pointString, String directionString)
            throws MalformedException {
        this.shipClass = shipClass;
        this.startPoint = PointDecoder.inputToPoint(pointString);
        this.direction = PointDecoder.inputToDirection(directionString);
    }

    @Override
    public List<Point> resolve() throws MalformedException {
        return new PointsSetter(shipClass.getSize(), startPoint, direction).set();
    }

    @Override
    public Point getStartPoint() {
        return startPoint;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public ShipClass getShipClass() {
        return shipClass;
    }
}

package battleship.v1.point;

import java.util.List;
import java.util.Map;

import battleship.ship.ShipClass;

public class InputPointsResolver implements PointsResolver {
    private static Map<Character, Direction> charToShipClass;
    private final ShipClass shipClass;
    private final String pointString;
    static {
        charToShipClass.put('l', Direction.LEFT);
        charToShipClass.put('r', Direction.RIGHT);
        charToShipClass.put('u', Direction.UP);
        charToShipClass.put('d', Direction.DOWN);
    }

    public InputPointsResolver(ShipClass shipClass, String pointString) {
        this.shipClass = shipClass;
        this.pointString = pointString;
    }

    @Override
    public List<Point> resolve() throws IllegalArgumentException{
        char posY = pointString.charAt(0);
        String posX = pointString.substring(1, 2);
        char posDirection = pointString.charAt(2);
        int y = (int) posY - (int) 'a' - 1;
        int x = Integer.parseInt(posX) - 1;
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            throw new IllegalArgumentException("Ivalid position value");
        }
        if (!charToShipClass.containsKey(posDirection)) {
            throw new IllegalArgumentException("Invalid direction value");
        }
        List<Point> points = new PointsSetter(shipClass.getSize(), new PointImpl(x, y),
                charToShipClass.get(posDirection)).set();

        return points;
    }

}

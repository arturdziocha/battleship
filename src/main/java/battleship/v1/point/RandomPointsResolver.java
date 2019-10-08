package battleship.v1.point;

import java.util.List;
import java.util.Random;

import battleship.v1.ship.ShipClass;

public class RandomPointsResolver implements PointsResolver {
    private final Random random = new Random();
    private final ShipClass shipClass;
    private final Point startPoint;
    private final Direction direction;

    public RandomPointsResolver(ShipClass shipClass) {
        this.shipClass = shipClass;
        this.direction = Direction.values()[random.nextInt(Direction.values().length)];
        this.startPoint = lotteryRowAndColumn(direction);
    }

    @Override
    public List<Point> resolve() {
        return new PointsSetter(shipClass.getSize(), startPoint, direction).set();
    }

    private Point lotteryRowAndColumn(Direction direction) {
        int row = 0;
        int column = 0;
        switch (direction) {
        case UP:
            row = random.nextInt(10) + shipClass.getSize();
            column = random.nextInt(10);
            break;
        case DOWN:
            row = random.nextInt(10 - shipClass.getSize());
            column = random.nextInt(10);
            break;
        case LEFT:
            row = random.nextInt(10);
            column = random.nextInt(10) + shipClass.getSize();
            break;
        case RIGHT:
            row = random.nextInt(10);
            column = random.nextInt(10) - shipClass.getSize();
            break;
        }
        return new PointImpl(row, column);
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

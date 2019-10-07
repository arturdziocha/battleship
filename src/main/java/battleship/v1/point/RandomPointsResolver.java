package battleship.v1.point;

import battleship.v1.ship.ShipClass;

import java.util.List;
import java.util.Random;

public class RandomPointsResolver implements PointsResolver {
    private final Random random = new Random();
    private final ShipClass shipClass;

    public RandomPointsResolver(ShipClass shipClass) {
        this.shipClass = shipClass;
    }

    @Override
    public List<Point> resolve() {
        Direction randomDirection = Direction.values()[random.nextInt(4)];
        Point startPoint = lotteryRowAndColumn(randomDirection);
        return new PointsSetter(shipClass.getSize(), startPoint, randomDirection).set();
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
}

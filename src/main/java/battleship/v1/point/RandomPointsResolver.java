package battleship.v1.point;

import java.util.List;
import java.util.Random;

import battleship.v1.ship.ShipClass;

public class RandomPointsResolver implements PointsResolver {
    private Random random = new Random();
    private final ShipClass shipClass;

    public RandomPointsResolver(ShipClass shipClass) {
        this.shipClass = shipClass;
    }

    @Override
    public List<Point> resolve() {
        Direction randomDirection = Direction.values()[random.nextInt(4)];
        Point startPoint = lotteryRowAndColumn(randomDirection);
        List<Point> points = new PointsSetter(shipClass.getSize(), startPoint, randomDirection).set();
        return points;
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
        case RIGHT:
            row = random.nextInt(10);
            column = random.nextInt(10) - shipClass.getSize();
        }
        return new PointImpl(row, column);
    }
}

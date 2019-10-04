package battleship.v1.point;

import java.util.ArrayList;
import java.util.List;

public class PointsSetter {
    private final int size;
    private final Point startPoint;
    private final Direction direction;
    private List<Point> points;

    public PointsSetter(int size, Point startPoint, Direction direction) {
        this.size = size;
        this.startPoint = startPoint;
        this.direction = direction;
        this.points = new ArrayList<>();
    }

    public List<Point> set() {
        switch (direction) {
        case UP:
            fillTop();
            break;
        case DOWN:
            fillBottom();
            break;
        case RIGHT:
            fillRight();
            break;
        case LEFT:
            fillLeft();
            break;
        }
        return points;
    }

    private void fillTop() {
        for (int i = 0; i < size; i++) {
            this.points.add(new PointImpl(startPoint.getRow() - i, startPoint.getColumn()));
        }
    }

    private void fillBottom() {
        for (int i = 0; i < size; i++) {
            this.points.add(new PointImpl(startPoint.getRow() + i, startPoint.getColumn()));
        }
    }

    private void fillRight() {
        for (int i = 0; i < size; i++) {
            this.points.add(new PointImpl(startPoint.getRow(), startPoint.getColumn() + i));
        }
    }

    private void fillLeft() {
        for (int i = 0; i < size; i++) {
            this.points.add(new PointImpl(startPoint.getRow(), startPoint.getColumn() - i));
        }

    }
}

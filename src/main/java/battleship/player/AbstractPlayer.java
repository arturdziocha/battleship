package battleship.player;

import java.util.Optional;
import java.util.stream.IntStream;

import battleship.exception.MalformattedException;
import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.Ship;

public abstract class AbstractPlayer implements Player {
    private PointStatus[][] shots;
    Fleet fleet;
    String name;

    AbstractPlayer() {
        shots = new PointStatus[10][10];
        IntStream.range(0, shots.length)
                .forEach(x -> IntStream.range(0, shots.length)
                        .forEach(y -> shots[x][y] = PointStatus.EMPTY));
    }

    @Override
    public Optional<Ship> shootToFleet(Point point) {
        return fleet.shipAt(point);
    }

    @Override
    public boolean hasLost() {
        return fleet.allShipsSunk();
    }

    @Override
    public void setShot(Point point, PointStatus pointStatus) {
        shots[point.getRow()][point.getColumn()] = pointStatus;
    }

    @Override
    public void setShotSunk(Ship ship) {
        ship.getPoints()
                .forEach(point -> shots[point.getRow()][point.getColumn()] = PointStatus.SUNK);
    }

    @Override
    public boolean isAlreadyShooted(Point point) {
        return !shots[point.getRow()][point.getColumn()].equals(PointStatus.EMPTY);
    }

    @Override
    public PointStatus[][] getShots() {
        return shots;
    }

    @Override
    public Fleet getFleet() {
        return fleet;
    }

    @Override
    public abstract Point prepareShot() throws MalformattedException;
}

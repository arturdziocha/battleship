package battleship.player;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import battleship.exception.NotAllShipsPlacedException;
import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.Ship;
import battleship.ship.ShipClass;

public abstract class AbstractPlayer implements Player {
    protected PointStatus[][] shots;
    protected Fleet fleet;
    protected String name;

    public AbstractPlayer() {
        shots = new PointStatus[10][10];
        IntStream.range(0, shots.length)
                .forEach(x -> IntStream.range(0, shots.length)
                        .forEach(y -> shots[x][y] = PointStatus.EMPTY));
    }   

    @Override
    public List<ShipClass> shipsToPlace() {
        return fleet.shipsToPlace();
    }

    public Optional<Ship> shootToFleet(Point point) throws NotAllShipsPlacedException {
        if (!fleet.isAllShipsPlaced()) {
            throw new NotAllShipsPlacedException("Not all ships placed");
        }
        return fleet.shipAt(point);
    }

    @Override
    public boolean hasLost() {
        return fleet.allShipsSunk();
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
    public void setShot(Point point, PointStatus pointStatus) {
        shots[point.getRow()][point.getColumn()] = pointStatus;
    }

    @Override
    public void setShotSunk(Ship ship) {
        ship.getPoints()
                .stream()
                .forEach(point -> shots[point.getRow()][point.getColumn()] = PointStatus.SUNK);
    }

    @Override
    public Fleet getFleet() {
        return fleet;
    }

}

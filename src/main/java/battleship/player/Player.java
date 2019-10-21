package battleship.player;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import battleship.exception.NotAllShipsPlacedException;
import battleship.fleet.Fleet;
import battleship.point.PointImpl;
import battleship.point.PointStatus;
import battleship.ship.Ship;
import battleship.ship.ShipClass;

public abstract class Player {    
    protected PointStatus[][] shots;
    protected Fleet fleet;
    protected String name;

    public Player() {        
        shots = new PointStatus[10][10];
        IntStream.range(0, shots.length)
                .forEach(x -> IntStream.range(0, shots.length)
                        .forEach(y -> shots[x][y] = PointStatus.EMPTY));
    }

    public Optional<Ship> shootToFleet(PointImpl point) throws NotAllShipsPlacedException {
        if (!fleet.isAllShipsPlaced()) {
            throw new NotAllShipsPlacedException("Not all ships placed");
        }
        return fleet.shipAt(point);
    }

    public boolean hasLost() {
        return fleet.allShipsSunk();
    }

    public boolean isAlreadyShooted(PointImpl point) {
        return !shots[point.getRow()][point.getColumn()].equals(PointStatus.EMPTY);
    }

    public List<ShipClass> shipsToPlace() {
        return fleet.shipsToPlace();
    }
    public PointStatus[][] getShots() {
        return shots;
    }
    public Fleet getFleet() {
        return fleet;
    }

}

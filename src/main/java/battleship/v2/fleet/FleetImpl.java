package battleship.v2.fleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import battleship.v2.exception.DirectionException;
import battleship.v2.exception.MalformedException;
import battleship.v2.exception.PointException;
import battleship.v2.point.Point;
import battleship.v2.ship.Ship;
import battleship.v2.ship.ShipClass;
import battleship.v2.ship.ShipImpl;

public class FleetImpl implements Fleet {
    private List<Ship> ships;

    public FleetImpl() {
        ships = new ArrayList<>();
    }

    @Override
    public boolean placeShip(Ship ship) {
        if (isOutsideBoard(ship) || isToClose(ship) || isAlreadyPlaced(ship)) {
            return false;
        } else {
            ships.add(ship);
            return true;
        }
    }

    @Override
    public void placeShipsRandom() throws MalformedException, DirectionException, PointException {
        while (!isAllShipsPlaced()) {
            for (ShipClass shipClass : ShipClass.values()) {
                Ship ship = new ShipImpl.Builder(shipClass).points()
                        .build();
                placeShip(ship);
            }
        }
    }

    @Override
    public List<Ship> getShips() {
        return ships;
    }

    @Override
    public boolean isAllShipsPlaced() {
        List<ShipClass> shipClasses = Arrays.asList(ShipClass.values())
                .stream()
                .sorted()
                .collect(Collectors.toList());
        List<ShipClass> fleetShipClasses = ships.stream()
                .map(Ship::getShipClass)
                .sorted()
                .collect(Collectors.toList());
        return shipClasses.equals(fleetShipClasses);
    }

    @Override
    public Optional<Ship> shipAt(Point point) {
        return ships.stream()
                .filter(s -> s.isAt(point)
                        .isPresent())
                .findAny();
    }

    private boolean isOutsideBoard(Ship ship) {
        return ship.getMostBottomPosition() >= SIZE || ship.getMostTopPosition() < 0
                || ship.getMostRightPosition() >= SIZE || ship.getMostLeftPosition() < 0;
    }

    private boolean isToClose(Ship ship) {
        for (Ship s : ships) {
            if (s.toCloseTo(ship)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAlreadyPlaced(Ship ship) {
        return ships.stream()
                .anyMatch(s -> s.getShipClass()
                        .equals(ship.getShipClass()));
    }

}

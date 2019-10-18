package battleship.fleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import battleship.exception.MalformattedException;
import battleship.exception.ShipOverlapException;
import battleship.point.Point;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import battleship.ship.ShipImpl;

public class FleetImpl implements Fleet {
    private List<Ship> ships;

    public FleetImpl() {
        ships = new ArrayList<>();
    }

    @Override
    public void placeShip(Ship ship) throws ShipOverlapException {
        if (ship == null) {
            throw new IllegalArgumentException("Tried to place a null ship.");
        }
        if (isOutsideBoard(ship)) {
            throw new ShipOverlapException("Ship is outside board");
        }
        if (isToClose(ship)) {
            throw new ShipOverlapException("Ship is to close another ship");
        }
        if (isAlreadyPlaced(ship)) {
            throw new ShipOverlapException("Ship is already set");
        }
        ships.add(ship);

    }

    @Override
    public void placeShipsRandom() throws MalformattedException {
        while (!isAllShipsPlaced()) {
            for (ShipClass shipClass : ShipClass.values()) {
                Ship ship = new ShipImpl.Builder(shipClass).points()
                        .build();
                while (isOutsideBoard(ship) || isToClose(ship) || isAlreadyPlaced(ship)) {
                    ship = new ShipImpl.Builder(shipClass).points()
                            .build();
                }
                ships.add(ship);
            }
        }
    }

    @Override
    public List<Ship> getShips() {
        return ships;
    }

    @Override
    public boolean isAllShipsPlaced() {
        List<ShipClass> shipClasses = getShipClasses();
        /* TODO this is the normal way */
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

    @Override
    public boolean allShipsSunk() {
        return ships.stream()
                .allMatch(ship -> ship.isSunk());
    }
    
    @Override
    public List<Ship> shipsToPlace() {
        // TODO Auto-generated method stub
        return null;
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
    private List<ShipClass> getShipClasses(){
        return Arrays.asList(ShipClass.values())
                .stream()                
                .collect(Collectors.toList());
    }
    private List<ShipClass> getFleetShipClasses(){
        return ships.stream()
                .map(Ship::getShipClass)                
                .collect(Collectors.toList());
    }
}

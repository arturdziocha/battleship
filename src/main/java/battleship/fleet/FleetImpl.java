package battleship.fleet;

import java.util.ArrayList;
import java.util.Arrays;
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

    private FleetImpl(Builder builder) {
        ships = builder.ships;
    }

    public static class Builder {
        private List<Ship> ships;

        public Fleet build() {
            this.ships = new ArrayList<>();
            return new FleetImpl(this);
        }
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
    public List<ShipClass> shipsToPlace() {
        List<ShipClass> shipClasses = getShipClassesSorted();
        List<ShipClass> fleetShipClasses = getFleetShipClassesSorted();
        return shipClasses.stream()
                .filter(shipCl -> !fleetShipClasses.contains(shipCl))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ship> getShips() {
        return ships;
    }

    @Override
    public boolean isAllShipsPlaced() {
        List<ShipClass> shipClasses = getShipClassesSorted();
        List<ShipClass> fleetShipClasses = getFleetShipClassesSorted();
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
                .allMatch(Ship::isSunk);
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

    private List<ShipClass> getShipClassesSorted() {
        return Arrays.stream(ShipClass.values())
                .sorted()
                .collect(Collectors.toList());
    }

    private List<ShipClass> getFleetShipClassesSorted() {
        return ships.stream()
                .map(Ship::getShipClass)
                .sorted()
                .collect(Collectors.toList());
    }
}

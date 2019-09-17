package battleship;

import battleship.interfaces.Fleet;
import battleship.interfaces.Ship;

import java.util.ArrayList;
import java.util.List;

public class FleetImpl implements Fleet {
    private List<Ship> ships;

    public FleetImpl() {
        ships = new ArrayList<>();
    }

    @Override
    public boolean placeShip(Ship ship) {
        if(ship.getSize() > SIZE){
            return false;
        }

        return true;
    }
}

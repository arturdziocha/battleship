package battleship.player;

import java.util.List;

import battleship.exception.NotAllShipsPlacedException;
import battleship.exception.ShipOverlapException;
import battleship.fleet.Fleet;
import battleship.ship.Ship;

public class ConsolePlayer extends Player {    
    public static class Builder{
        private String name;
        private Fleet fleet;
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder placeShip(Ship ship) throws ShipOverlapException {
            this.fleet.placeShip(ship);
            return this;
        }
        public Builder placeShips(List<Ship> list) throws ShipOverlapException {
            for(Ship ship : list) {
                this.fleet.placeShip(ship);
            }
            return this;
        }
        public ConsolePlayer build() throws NotAllShipsPlacedException {            
            return new ConsolePlayer(this);
        }
        
    }
    private ConsolePlayer(Builder builder) {
        super();
        this.name = builder.name;
        this.fleet = builder.fleet;
    }
}

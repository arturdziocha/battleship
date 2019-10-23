package battleship.player;

import battleship.exception.MalformattedException;
import battleship.exception.ShipOverlapException;
import battleship.fleet.Fleet;
import battleship.fleet.FleetImpl;
import battleship.point.PointImpl;

public class EasyComputerPlayer extends AbstractPlayer implements Computer {
    public static class Builder {
        private String name;
        private Fleet fleet;
        public Builder(Fleet fleet) {
            this.fleet = fleet;
        }
        public EasyComputerPlayer build() throws MalformattedException, ShipOverlapException {
            name = "Easy Computer";
            fleet = new FleetImpl.Builder().build();
            fleet.placeShipsRandom();
            
            return new EasyComputerPlayer(this);
        }
    }
    private EasyComputerPlayer(Builder builder) {
        super();
        this.name = builder.name; 
        fleet = builder.fleet;
    }

    

    @Override
    public PointImpl prepareShot() {
        // TODO Auto-generated method stub
        return null;
    }

}

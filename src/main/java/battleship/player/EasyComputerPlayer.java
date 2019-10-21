package battleship.player;

import battleship.exception.MalformattedException;
import battleship.exception.ShipOverlapException;
import battleship.fleet.Fleet;
import battleship.fleet.FleetImpl;
import battleship.point.PointImpl;

public class EasyComputerPlayer extends Player implements Computer {
    public static class Builder {
        private String name;
        private Fleet fleet;

        public EasyComputerPlayer build() throws MalformattedException, ShipOverlapException {
            name = "Easy Computer";
            fleet = new FleetImpl.Builder().build();
            fleet.placeShipsRandom();

            return new EasyComputerPlayer(this);
        }
    }
    private EasyComputerPlayer(Builder builder) throws MalformattedException, ShipOverlapException {
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

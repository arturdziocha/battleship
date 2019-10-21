package battleship.player;

import battleship.exception.MalformattedException;
import battleship.exception.ShipOverlapException;
import battleship.fleet.Fleet;
import battleship.fleet.FleetImpl;
import battleship.point.PointImpl;
import battleship.point.PointStatus;

public class HardComputerPlayer extends Player implements Computer {
    private PointImpl lastShot;
    private PointStatus lastShotStatus;

    public static class Builder {
        private String name;
        private Fleet fleet;

        public HardComputerPlayer build() throws MalformattedException, ShipOverlapException {
            name = "Hard Computer";
            fleet = new FleetImpl.Builder().build();
            fleet.placeShipsRandom();
            return new HardComputerPlayer(this);
        }
    }

    private HardComputerPlayer(Builder builder) throws MalformattedException, ShipOverlapException {
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

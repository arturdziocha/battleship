package battleship.player;

import battleship.exception.MalformattedException;
import battleship.exception.ShipPlacementException;
import battleship.fleet.Fleet;
import battleship.fleet.FleetImpl;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.point.PointStatus;

public class HardComputerPlayer extends AbstractPlayer {
    private PointImpl lastShot;
    private PointStatus lastShotStatus;

    public static class Builder {
        private String name;
        private Fleet fleet;

        public Builder(Fleet fleet) {
            this.fleet = fleet;
        }

        public HardComputerPlayer build() {
            name = "Hard Computer";
            return new HardComputerPlayer(this);
        }
    }

    private HardComputerPlayer(Builder builder) {
        super();
        this.name = builder.name;
        fleet = builder.fleet;
    }

    @Override
    public Point prepareShot() {
        // TODO Auto-generated method stub
        return null;
    }
}

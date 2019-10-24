package battleship.player;

import battleship.exception.MalformattedException;
import battleship.exception.ShipPlacementException;
import battleship.fleet.Fleet;
import battleship.fleet.FleetImpl;
import battleship.point.Point;
import battleship.point.PointImpl;

public class EasyComputerPlayer extends AbstractPlayer {
    public static class Builder {
        private String name;
        private Fleet fleet;

        public Builder(Fleet fleet) {
            this.fleet = fleet;
        }

        public EasyComputerPlayer build() throws MalformattedException, ShipPlacementException {
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
    public Point prepareShot() {
        Point point = new PointImpl.Builder().build();
        while (isAlreadyShooted(point)) {
            point = new PointImpl.Builder().build();
        }
        return point;
    }

}

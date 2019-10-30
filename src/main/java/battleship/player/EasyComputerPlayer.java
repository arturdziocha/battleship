package battleship.player;

import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointImpl;

public class EasyComputerPlayer extends AbstractPlayer {
    public static class Builder {
        private String name;
        private Fleet fleet;

        public Builder(Fleet fleet) {
            this.fleet = fleet;
        }

        public EasyComputerPlayer build() {
            name = "Easy Computer";
            return new EasyComputerPlayer(this);
        }
    }

    private EasyComputerPlayer(Builder builder) {
        super();
        this.name = builder.name;
        fleet = builder.fleet;
    }
    @Override
    public String getName() {
        return name;
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

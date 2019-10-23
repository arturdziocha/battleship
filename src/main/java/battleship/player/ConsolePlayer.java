package battleship.player;

import battleship.exception.MalformattedException;
import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.view.ConsoleReader;

public class ConsolePlayer extends AbstractPlayer {
    public static class Builder {
        private String name;
        private Fleet fleet;

        public Builder(String name, Fleet fleet) {
            this.name = name;
            this.fleet = fleet;
        }

        public ConsolePlayer build() {
            return new ConsolePlayer(this);
        }
    }

    private ConsolePlayer(Builder builder) {
        super();
        name = builder.name;
        fleet = builder.fleet;
    }

    @Override
    public Point prepareShot() throws MalformattedException {
        String pointString = ConsoleReader.shotPointReader();
        return new PointImpl.Builder(pointString).build();
    }
}

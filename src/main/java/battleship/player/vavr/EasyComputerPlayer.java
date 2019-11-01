package battleship.player.vavr;

import battleship.fleet.vavr.Fleet;
import battleship.point.vavr.Point;
import battleship.point.vavr.PointImpl;
import io.vavr.control.Either;

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
    public Either<String, Point> prepareShot() {
        Either<String, Point> point = new PointImpl.Builder().build();
        while (!isAlreadyShooted(point.get())) {
            point = new PointImpl.Builder().build();
        }
        return point;
    }

}

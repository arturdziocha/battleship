package battleship.player;

import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.point.PointStatus;
import io.vavr.control.Either;

public class HardComputerPlayer extends AbstractPlayer {
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

	private Point lastShot;
	private PointStatus lastShotStatus;

	private HardComputerPlayer(Builder builder) {
		super();
		this.name = builder.name;
		fleet = builder.fleet;
	}

	@Override
    public Either<String, Point> prepareShot() {
        Either<String, Point> point = new PointImpl.Builder().build();
        while (isAlreadyShooted(point.get())) {
            point = new PointImpl.Builder().build();
        }
        return point;
    }

}

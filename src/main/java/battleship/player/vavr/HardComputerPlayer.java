package battleship.player.vavr;

import battleship.fleet.vavr.Fleet;
import battleship.point.PointImpl;
import battleship.point.PointStatus;
import battleship.point.vavr.Point;
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

	private PointImpl lastShot;
	private PointStatus lastShotStatus;

	private HardComputerPlayer(Builder builder) {
		super();
		this.name = builder.name;
		fleet = builder.fleet;
	}

	@Override
	public Either<String, Point> prepareShot() {
		// TODO Auto-generated method stub
		return null;
	}

}

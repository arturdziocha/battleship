package battleship.player;

import battleship.fleet.Fleet;
import battleship.point.Point;
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
		// TODO Auto-generated method stub
		return null;
	}

}

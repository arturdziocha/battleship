package battleship.player;

import java.util.Scanner;

import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.view.ConsoleView;
import battleship.view.View;
import io.vavr.control.Either;

public class ConsolePlayer extends AbstractPlayer {

	public static class Builder {
		private String name;
		private Fleet fleet;
		private View view;

		public Builder(String name, Fleet fleet) {
			this.name = name;
			this.fleet = fleet;
			this.view = new ConsoleView();
		}

		public ConsolePlayer build() {
			return new ConsolePlayer(this);
		}
	}

	private final View view;
	private Scanner scanner;

	private ConsolePlayer(Builder builder) {
		super();
		name = builder.name;
		fleet = builder.fleet;
		view = builder.view;
	}

	@Override
	public Either<String, Point> prepareShot() {
		scanner = new Scanner(System.in);
		view.showShotPointView(name);
		String pointString = scanner.next();
		return new PointImpl.Builder(pointString).build();
	}

}

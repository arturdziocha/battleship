package battleship.player;

import java.util.Scanner;

import battleship.exception.MalformattedException;
import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.view.ConsoleView;
import battleship.view.View;

public class ConsolePlayer extends AbstractPlayer {
    private View view;
    private Scanner scanner;
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

    private ConsolePlayer(Builder builder) {
        super();
        name = builder.name;
        fleet = builder.fleet;
        view = builder.view;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Point prepareShot() throws MalformattedException {
        scanner = new Scanner(System.in);
        view.showShotPointView(name);
        String pointString = scanner.next();        
        return new PointImpl.Builder(pointString).build();
    }
}

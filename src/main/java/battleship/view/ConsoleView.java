package battleship.view;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import battleship.direction.Direction;
import battleship.fleet.Fleet;
import battleship.point.PointStatus;
import battleship.ship.ShipClass;

public class ConsoleView implements View {
    private String[] letters = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

    @Override
    public void welcomeUsers() {
        System.out.println("WELCOME TO BATTLESHIPS\n\n");
    }

    @Override
    public void showInstructions() {
        System.out.println("INSTRUCTIONS\n" + "- Before the game starts you will be asked to place your battleships\n"
                + "- When the game starts you can select a position on the enemy board to fire on\n"
                + "- You cannot fire in the same place twice\n"
                + "- The game is only over when either you or your enemy have no battleships are left\n");
    }

    @Override
    public void showGameMode() {
        System.out.println("Please select game mode\n" + "[0] - Human vs Human\n" + "[1] - Human vs Easy Computer\n"
                + "[2] - Human vs Gard Computer\n");

    }

    @Override
    public void showNameSelect() {
        System.out.println("Please give your name:\n");
        System.out.println(">>>");
        
    }

    @Override
    public void showShipPlacementModeView(String playerName) {
        System.out.println(playerName + ": would you like to set the ships yourself\n" + "[0] - YES\n" + "[1] - Set randomly\n"
                + ">>>");
    }

    @Override
    public void showShipsToPlace(String playerName, List<ShipClass> shipsToPlace) {
        System.out.println(playerName + ": please select ship to place");
        IntStream.range(0, shipsToPlace.size())
                .forEach(i -> System.out.println("[" + i + "]" + shipsToPlace.get(i)
                        .name()));

    }

    @Override
    public void showShipPositioningView(ShipClass shipClass) {
        System.out.println("Please set the position and direction of ship " + shipClass + ": length - " + shipClass.getSize()
                + "\n" + "Position should be between A-J and between 1-10\n");

        System.out.println("Position should be like A3\n");
        System.out.println(">>>");
    }

    @Override
    public void showShipDirectionView(ShipClass shipClass) {
        System.out.println("Please set the direction of ship " + shipClass + "length: " + shipClass.getSize() + "\n");
        for (Direction direction : Direction.values()) {
            System.out.print("[" + direction.getShortName() + "]: " + direction + "\n ");
        }
        ;
        System.out.println(">>>");

    }

    @Override
    public void showFleetShips(Fleet fleet) {
        PointStatus[][] points = new PointStatus[10][10];
        IntStream.range(0, 10)
                .forEach(row -> {
                    IntStream.range(0, 10)
                            .forEach(column -> points[row][column] = PointStatus.EMPTY);
                });
        fleet.getShips()
                .stream()
                .flatMap(ship -> ship.getPoints()
                        .stream())
                .forEach(point -> points[point.getRow()][point.getColumn()] = PointStatus.OCCUPIED);

        System.out.printf("%-3s", "");
        Stream.of(letters)
                .forEach(letter -> System.out.printf("%-3s", " " + letter));
        System.out.println();

        IntStream.range(0, 10)
                .forEach(row -> {
                    System.out.printf("%-3s", row + 1);
                    IntStream.range(0, 10)
                            .forEach(column -> System.out.printf("%-3s", "[" + points[row][column].getStatus() + "]"));
                    System.out.println();

                });

        /*
         * for (PointStatus[] arr : points) { System.out.printf("%-3d", 1); for
         * (PointStatus status : arr) { System.out.printf("%-3s", "[" +
         * status.getStatus() + "]");
         * 
         * } System.out.println(); }
         */
    }

}

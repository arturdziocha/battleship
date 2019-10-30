package battleship.view;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import battleship.direction.Direction;
import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.ShipClass;

public class ConsoleView implements View {
    public String[] letters = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
    public static String yellow = ConsoleColor.ANSI_YELLOW + "[%s]" + ConsoleColor.ANSI_RESET + " ";
    public static String blue = ConsoleColor.ANSI_BLUE + "%s" + ConsoleColor.ANSI_RESET + "\n";
    public static String cyan = ConsoleColor.ANSI_CYAN + "%s" + ConsoleColor.ANSI_RESET + "\n";
    public static String error = ConsoleColor.ANSI_RED + "%s" + ConsoleColor.ANSI_RESET + "\n";

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
        System.out.printf(cyan, "Please select game mode");
        System.out.println("[0] - Human vs Human");
        System.out.println("[1] - Human vs Easy Computer");
        System.out.println("[2] - Human vs Gard Computer");
        System.out.printf(blue, ">>>");

    }

    @Override
    public void showNameSelect(int playerId) {
        System.out.printf(cyan, "Player " + playerId + " - give your name");
        System.out.printf(blue, ">>>");

    }

    @Override
    public void showShipPlacementModeView(String playerName) {
        System.out.printf(yellow, playerName);
        System.out.printf(cyan, "Would you like to set the ships yourself");
        System.out.println("[0] - Yes");
        System.out.println("[1] - Set randomly");
        System.out.printf(blue, ">>>");
    }

    @Override
    public void showShipsToPlace(String playerName, List<ShipClass> shipsToPlace) {
        System.out.printf(yellow, playerName);
        System.out.printf(cyan, "Please select ship to place");
        IntStream.range(0, shipsToPlace.size())
                .forEach(i -> System.out.println("[" + i + "]" + shipsToPlace.get(i)
                        .getName()));
        System.out.printf(blue, ">>>");

    }

    @Override
    public void showShipPositioningView(ShipClass shipClass) {
        System.out.printf(cyan, "Please set the position and direction of ship " + shipClass.getName() + " with length "
                + shipClass.getSize() + "\n" + "Position should be between A-J and between 1-10");

        System.out.printf(cyan, "Position should be like A3");
        System.out.printf(blue, ">>>");
    }

    @Override
    public void showShipDirectionView(ShipClass shipClass) {
        System.out.printf(cyan,
                "Please set the direction of ship " + shipClass.getName() + " with length " + shipClass.getSize());
        for (Direction direction : Direction.values()) {
            System.out.println("[" + direction.getShortName() + "]: " + direction);
        }
        ;
        System.out.printf(blue, ">>>");

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
                            .forEach(column -> {
                                if (points[row][column].equals(PointStatus.EMPTY)) {
                                    System.out.printf("[ ]");
                                } else {
                                    System.out.printf("[");
                                    System.out.printf(ConsoleColor.ANSI_CYAN + "%s" + ConsoleColor.ANSI_RESET,
                                            points[row][column].getStatus());
                                    System.out.printf("]");
                                }
                            });
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

    @Override
    public void showShotPointView(String playerName) {
        System.out.printf(ConsoleView.yellow, playerName);
        System.out.printf(ConsoleView.cyan,
                "Please enter a point to shot like 'B1' Column must be between A-J, row between 1-10");
        System.out.printf(ConsoleView.blue, ">>>");

    }

    @Override
    public void showShots(Map<Point, PointStatus> shots, String playerName) {
        System.out.printf(yellow, playerName);
        System.out.printf(cyan, "This is your shot board");
        PointStatus[][] shotsBoard = new PointStatus[10][10];

        IntStream.range(0, 10)
                .forEach(row -> {
                    IntStream.range(0, 10)
                            .forEach(column -> shotsBoard[row][column] = PointStatus.EMPTY);
                });
        shots.forEach((k, v) -> {
            shotsBoard[k.getRow()][k.getColumn()] = v;
        });
        System.out.printf("%-3s", "");
        Stream.of(letters)
                .forEach(letter -> System.out.printf("%-3s", " " + letter));
        System.out.println();
        IntStream.range(0, 10)
                .forEach(row -> {
                    System.out.printf("%-3s", row + 1);
                    IntStream.range(0, 10)
                            .forEach(column -> {
                                System.out.printf("[");
                                if (shotsBoard[row][column].equals(PointStatus.EMPTY)) {
                                    System.out.printf(" ");
                                } else if (shotsBoard[row][column].equals(PointStatus.HIT)) {
                                    System.out.printf(ConsoleColor.ANSI_CYAN + "%s" + ConsoleColor.ANSI_RESET,
                                            shotsBoard[row][column].getStatus());
                                } else if (shotsBoard[row][column].equals(PointStatus.SUNK)) {
                                    System.out.printf(ConsoleColor.ANSI_RED + "%s" + ConsoleColor.ANSI_RESET,
                                            shotsBoard[row][column].getStatus());
                                } else {
                                    System.out.printf(shotsBoard[row][column].getStatus());
                                }
                                System.out.printf("]");
                            });
                    System.out.println();

                });
    }

}

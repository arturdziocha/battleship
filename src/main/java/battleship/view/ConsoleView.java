package battleship.view;

import java.util.List;
import java.util.stream.IntStream;

import battleship.ship.ShipClass;

public class ConsoleView implements View {
    @Override
    public void welcomeUsers() {
        System.out.println("       WELCOME TO BATTLESHIPS\n\n");
    }

    @Override
    public void showInstructions() {
        System.out.println("INSTRUCTIONS\n" + "- Before the game starts you will be asked to place your battleships\n"
                + "- When the game starts you can select a position on the enemy board to fire on\n"
                + "- You cannot fire in the same place twice\n"
                + "- The game is only over when either you or your enemy have no battleships are left\n"
                + "- You may also resign from a game by typing \"resign\"\n" + "~ GOOD LUCK PLAYER ~\n");
    }

    @Override
    public void showGameMode() {
        System.out.println("Please select game mode\n" + "[0] - Human vs Human\n" + "[1] - Human vs Easy Computer\n"
                + "[2] - Human vs Gard Computer\n" + ">>>");

    }

    @Override
    public void showNameSelect() {
        System.out.println("Please give your name:\n" + ">>>");
    }

    @Override
    public void showShipPlacementMode() {
        System.out.println("Would you like to set the ships yourself\n" + "[0] - YES" + "[1] - Set randomly\n" + ">>>");
    }

    @Override
    public void showShipsToPlace(List<ShipClass> shipsToPlace) {
        System.out.println("Please select Ship to place");
        IntStream.range(0, shipsToPlace.size())
                .forEach(i -> System.out.println("[" + i + "]" + shipsToPlace.get(i)
                        .name()));

    }

    @Override
    public void showShipPositioningView(ShipClass shipClass) {
        System.out.println("Please set the position of ship " + shipClass.name() + ": length - " + shipClass.getSize()
                + "\n" + "Position should be between A-J and between 1-10\n" + ">>>");
    }

}

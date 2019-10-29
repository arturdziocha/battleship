package battleship.game;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import battleship.direction.Direction;
import battleship.exception.MalformattedException;
import battleship.exception.ShipPlacementException;
import battleship.fleet.Fleet;
import battleship.fleet.FleetImpl;
import battleship.player.ConsolePlayer;
import battleship.player.EasyComputerPlayer;
import battleship.player.HardComputerPlayer;
import battleship.player.Player;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import battleship.ship.ShipImpl;
import battleship.view.UI;
import battleship.view.View;

public class ConsoleGame implements Game {
    final Logger logger = LoggerFactory.getLogger(ConsoleGame.class);
    private Player firstPlayer, secondPlayer, currentPlayer;
    private Scanner reader = new Scanner(System.in);
    private final View view;

    public ConsoleGame(View view) {
        this.view = view;
    }

    @Override
    public void play() {
        view.welcomeUsers();
        view.showInstructions();
        int gameMode = setupGameMode();        
        setupPlayers(gameMode);
        // currentPlayer = firstPlayer;
    }

    private int setupGameMode() {
        boolean gameModeChecker = false;
        int gameMode = -1;
        while (gameModeChecker == false) {
            view.showGameMode();
            if (reader.hasNextInt()) {
                gameMode = reader.nextInt();
                if (gameMode == 0 || gameMode == 1 || gameMode == 2) {
                    gameModeChecker = true;
                } else {
                    System.out.println("Please select valid Game Mode");
                    gameModeChecker = false;
                    
                }

            } else {
                System.out.println("Enter valid integer value");
                reader.next();
            }

        }
        return gameMode;
    }

    private void setupPlayers(int setupMode) {

        switch (setupMode) {
        case 0:
            setupHumanPlayers();
            break;
        case 1:
        case 2:
            setupHumanWithComputer(setupMode);
            break;

        }
    }

    private void setupHumanPlayers() {
        firstPlayer = setupPlayer();
        UI.clear();
        secondPlayer = setupPlayer();

    }

    private void setupHumanWithComputer(int setupMode) {
        firstPlayer = setupPlayer();
        Fleet secondPlayerFleet = setupRandomShips(new FleetImpl.Builder().build());
        secondPlayer = setupMode == 1 ? new EasyComputerPlayer.Builder(secondPlayerFleet).build()
                : new HardComputerPlayer.Builder(secondPlayerFleet).build();

    }

    private Player setupPlayer() {
        String name = setupName();
        Fleet fleet = setupFleet(name);

        return new ConsolePlayer.Builder(name, fleet).build();
    }

    private String setupName() {
        view.showNameSelect();
        return reader.next();
    }

    private Fleet setupFleet(String playerName) {
        Fleet fleet = new FleetImpl.Builder().build();
        int placementMode = setupPlacementMode(playerName);

        if (placementMode == 1) {
            fleet = setupRandomShips(fleet);
        } else if (placementMode == 0) {
            fleet = setupConsoleFleet(fleet, playerName);
        }
        return fleet;
    }

    private int setupPlacementMode(String playerName) {
        boolean placementModeChecker = false;
        int placementMode = -1;
        while (placementModeChecker == false) {
            view.showShipPlacementModeView(playerName);
            if (reader.hasNextInt()) {
                placementMode = reader.nextInt();
                if (placementMode == 0 || placementMode == 1) {
                    placementModeChecker = true;
                } else {
                    System.out.println("Please select valid Placement mode");
                }
            } else {
                System.out.println("Enter valid valid integer value");
                reader.next();
            }
        }
        return placementMode;
    }

    private Fleet setupRandomShips(Fleet fleet) {
        while (!fleet.isAllShipsPlaced()) {
            try {
                fleet.placeShipsRandom();
            } catch (MalformattedException e) {
                System.out.println(e.getMessage());
            }
        }
        return fleet;
    }

    private Fleet setupConsoleFleet(Fleet fleet, String playerName) {
        while (!fleet.isAllShipsPlaced()) {
            ShipClass shipClass = setupShipToPlace(fleet, playerName);
            Point startPoint = setupPoint(shipClass);
            Direction direction = setupDirection(shipClass);
            try {
                Ship ship = new ShipImpl.Builder(shipClass).points(startPoint, direction).build();
                fleet.placeShip(ship);
            }catch (MalformattedException e) {
                System.out.println(e.getMessage());
            }catch(ShipPlacementException e) {
                System.out.println(e.getMessage());
            }
        }
        return fleet;       
    }

    private ShipClass setupShipToPlace(Fleet fleet, String playerName) {

        ShipClass shipClass = ShipClass.BARCA;
        boolean shipToPlaceChecker = false;
        while (shipToPlaceChecker == false) {
            view.showFleetShips(fleet);
            view.showShipsToPlace(playerName, fleet.shipsToPlace());
            if (reader.hasNextInt()) {
                int shipIdToPlace = reader.nextInt();
                if (shipIdToPlace >= 0 && shipIdToPlace < fleet.shipsToPlace()
                        .size()) {
                    shipClass = fleet.shipsToPlace()
                            .get(shipIdToPlace);
                    shipToPlaceChecker = true;
                    return shipClass;
                } else {
                    System.out.println("Please select valid Placement mode");
                }
            } else {
                System.out.println("Enter valid valid integer value");
                reader.next();
            }
        }
        return null;
        
    }

    private Point setupPoint(ShipClass shipClass) {
        boolean pointChecker = false;
        while (pointChecker == false) {
            view.showShipPositioningView(shipClass);
            if (reader.hasNext()) {
                try {
                    Point point = new PointImpl.Builder(reader.next()).build();
                    pointChecker = true;
                    return point;
                } catch (MalformattedException e) {
                    System.out.println(e.getMessage());
                    pointChecker = false;
                }
            } else {
                reader.next();
            }
        }
        return null;
    }

    private Direction setupDirection(ShipClass shipClass) {
        boolean directionChecker = false;
        while (directionChecker == false) {
            view.showShipDirectionView(shipClass);
            if (reader.hasNext()) {
                try {
                    Direction direction = Direction.getFromShortName(reader.next()
                            .charAt(0));
                    directionChecker = true;
                    return direction;
                } catch (MalformattedException e) {
                    System.out.println(e.getMessage());
                    directionChecker = false;
                }
            } else {
                reader.next();
            }
        }
        return Direction.DOWN;
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
    }

}

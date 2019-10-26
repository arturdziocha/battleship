package battleship.game;

import java.util.List;
import java.util.Scanner;

import battleship.direction.Direction;
import battleship.exception.MalformattedException;
import battleship.fleet.Fleet;
import battleship.fleet.FleetImpl;
import battleship.player.ConsolePlayer;
import battleship.player.EasyComputerPlayer;
import battleship.player.HardComputerPlayer;
import battleship.player.Player;
import battleship.point.Point;
import battleship.point.PointImpl;
import battleship.ship.ShipClass;
import battleship.view.View;

public class ConsoleGame implements Game {
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
        view.showGameMode();
        int mode = reader.nextInt();
        setupPlayers(mode);
        System.out.println(mode);
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

    }

    private void setupHumanWithComputer(int setupMode) {
        firstPlayer = setupPlayer(1);
        Fleet secondPlayerFleet = new FleetImpl.Builder().build();
        try {
            secondPlayerFleet.placeShipsRandom();
            secondPlayer = setupMode == 1 ? new EasyComputerPlayer.Builder(secondPlayerFleet).build()
                    : new HardComputerPlayer.Builder(secondPlayerFleet).build();
        } catch (MalformattedException e) {
            System.out.println(e.getMessage());
        }
        
    }

    private Player setupPlayer(int playerNumber) {
        String name = setupName();
        Fleet fleet = setupFleet();
        
        return new ConsolePlayer.Builder(name, fleet).build();
    }

    private String setupName() {
        view.showNameSelect();
        return reader.next();
    }
    private Fleet setupFleet() {
        Fleet fleet = new FleetImpl.Builder().build();

        int placementMode;
        do {
            view.showShipPlacementMode();
            placementMode = reader.nextInt();
        } while (placementMode == 0 || placementMode == 1);

        if (placementMode == 1) {
            try {
                do {
                    fleet.placeShipsRandom();
                } while (fleet.isAllShipsPlaced());
            } catch (MalformattedException e) {
                System.out.println(e.getMessage());
            }

        } else if (placementMode == 0) {
            while (!fleet.isAllShipsPlaced()) {
                List<ShipClass> shipsToPlace = fleet.shipsToPlace();

                int shipIdToPlace;
                do {
                    view.showShipsToPlace(shipsToPlace);
                    shipIdToPlace = reader.nextInt();
                } while (shipIdToPlace < shipsToPlace.size() && shipIdToPlace >= 0);

                ShipClass shipClass = shipsToPlace.get(shipIdToPlace);

                view.showShipPositioningView(shipClass);
                boolean pointChecker = false;
                while(pointChecker == false) {
                    try {
                        String pointString = reader.next();
                        String directionString = reader.next();
                        Point point = new PointImpl.Builder(pointString).build();
                        Direction direction = Direction.getFromShortName(directionString.charAt(0));
                        pointChecker = true;
                    }catch(MalformattedException e) {
                        System.out.println(e.getMessage());
                        pointChecker = false;
                    }                    
                }
                
                
            }
        }
        return fleet;
    }
    

}

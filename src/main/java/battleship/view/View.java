package battleship.view;

import java.util.List;

import battleship.ship.ShipClass;

public interface View {
    void welcomeUsers();
    void showInstructions();
    void showGameMode();
    void showNameSelect();
    void showShipPlacementMode();
    void showShipsToPlace(List<ShipClass> shipsToPlace);
    void showShipPositioningView(ShipClass shipClass);
    
}

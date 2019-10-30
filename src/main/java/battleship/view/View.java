package battleship.view;

import java.util.List;
import java.util.Map;

import battleship.fleet.Fleet;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.ShipClass;

public interface View {
    void welcomeUsers();
    void showInstructions();
    void showGameMode();
    void showNameSelect(int playerId);
    void showShipPlacementModeView(String playerName);
    void showShipsToPlace(String playerName, List<ShipClass> shipsToPlace);
    void showShipPositioningView(ShipClass shipClass);
    void showShipDirectionView(ShipClass shipClass);
	void showFleetShips(Fleet fleet);
    void showShots(Map<Point, PointStatus> shots, String playerName);
    void showShotPointView(String name);
    
}

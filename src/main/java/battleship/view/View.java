package battleship.view;

import battleship.fleet.Fleet;
import battleship.player.Player;
import battleship.point.Point;
import battleship.point.PointStatus;
import battleship.ship.ShipClass;
import io.vavr.collection.List;
import io.vavr.collection.Map;

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

	void showShotPointView(String playerName);
	
	void showWinner(Player player);

}

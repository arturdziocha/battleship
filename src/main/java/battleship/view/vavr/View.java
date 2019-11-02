package battleship.view.vavr;

import battleship.fleet.vavr.Fleet;
import battleship.player.Player;
import battleship.point.vavr.Point;
import battleship.point.vavr.PointStatus;
import battleship.ship.vavr.ShipClass;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Set;

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

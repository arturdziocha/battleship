package battleship.cmdImpl;

import battleship.Fleet;
import battleship.Player;
import battleship.Ship;

public class PlayerImpl implements Player {
	private String name;
	private Fleet fleet;

	public PlayerImpl(String name) {
		this.name = name;
		this.fleet = new FleetImpl();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean placeShip(Ship ship) {
		return fleet.placeShip(ship);
	}

	@Override
	public Fleet getFleet() {
		return fleet;
	}

}

package battleship;

public interface Player {
	String getName();
	boolean placeShip(Ship ship);
	Fleet getFleet();
	
	
}

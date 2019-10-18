package battleship.player;

import battleship.exception.MalformattedException;
import battleship.exception.ShipOverlapException;
import battleship.point.Point;
import battleship.point.PointStatus;

public class HardComputerPlayer extends Player implements Computer {
    private Point lastShot;
    private PointStatus lastShotStatus;
    
    public HardComputerPlayer() throws MalformattedException, ShipOverlapException {
        super();
        this.name = "Hard Computer";
        fleet.placeShipsRandom();
    }
    @Override
    public Point prepareShot() {
        // TODO Auto-generated method stub
        return null;
    }

}

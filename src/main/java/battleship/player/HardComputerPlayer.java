package battleship.player;

import battleship.exception.DirectionException;
import battleship.exception.MalformedException;
import battleship.exception.PointException;
import battleship.point.Point;
import battleship.point.PointStatus;

public class HardComputerPlayer extends Player implements Computer {
    private Point lastShot;
    private PointStatus lastShotStatus;
    
    public HardComputerPlayer() throws MalformedException, DirectionException, PointException {
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

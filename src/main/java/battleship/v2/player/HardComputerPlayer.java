package battleship.v2.player;

import battleship.v2.exception.DirectionException;
import battleship.v2.exception.MalformedException;
import battleship.v2.exception.PointException;
import battleship.v2.point.Point;
import battleship.v2.point.PointStatus;

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

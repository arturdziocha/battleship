package battleship.player;

import battleship.exception.DirectionException;
import battleship.exception.MalformedException;
import battleship.exception.PointException;
import battleship.point.Point;

public class EasyComputerPlayer extends Player implements Computer {
    public EasyComputerPlayer() throws MalformedException, DirectionException, PointException {
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

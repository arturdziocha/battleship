package battleship.v2.player;

import battleship.v2.exception.DirectionException;
import battleship.v2.exception.MalformedException;
import battleship.v2.exception.PointException;
import battleship.v2.point.Point;

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

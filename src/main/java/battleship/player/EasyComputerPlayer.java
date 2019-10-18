package battleship.player;

import battleship.exception.MalformattedException;
import battleship.exception.ShipOverlapException;
import battleship.point.Point;

public class EasyComputerPlayer extends Player implements Computer {
    public EasyComputerPlayer() throws MalformattedException, ShipOverlapException{
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

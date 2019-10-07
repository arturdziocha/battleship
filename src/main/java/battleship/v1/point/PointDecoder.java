package battleship.v1.point;

import battleship.v1.MalformedException;
import org.apache.commons.lang3.StringUtils;

public class PointDecoder {
    public static Point inputToPoint(String input) throws MalformedException {
        if (StringUtils.isEmpty(input)) {
            throw new MalformedException("Input cannot be empty");
        }
        input = input.toLowerCase();
        char posY = input.charAt(0);
        String posX = input.substring(1);

        int y = (int) posY - (int) 'a';
        try {
            int x = Integer.parseInt(posX) - 1;
            if (x < 0 || x > 9 || y < 0 || y > 9) {
                throw new MalformedException("First character must be between A and J");
            }
            return new PointImpl(x, y);
        } catch (NumberFormatException ex) {
            throw new MalformedException("Malformed number");
        }
    }

    public static Direction inputToDirection(String input) {
        char charDirection = input.charAt(0);
        switch (charDirection) {
            case 'l':
                return Direction.LEFT;
            case 'r':
                return Direction.RIGHT;
            case 'd':
                return Direction.DOWN;
            case 'u':
                return Direction.UP;
            default:
                throw new IllegalArgumentException("Direction doesn't match");
        }
    }
}

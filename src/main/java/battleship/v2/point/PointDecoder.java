package battleship.v2.point;

import battleship.v2.MalformedException;
import org.apache.commons.lang3.StringUtils;

public class PointDecoder {
    public static Point inputToPoint(String input) throws MalformedException {
        if (StringUtils.isEmpty(input)) {
            throw new MalformedException("Point cannot be empty");
        }
        input = input.toLowerCase();
        char posY = input.charAt(0);
        String posX = input.substring(1);

        int y = (int) posY - (int) 'a';
        try {
            int x = Integer.parseInt(posX) - 1;
            if (x < 0 || x > 9 || y < 0 || y > 9) {
                throw new MalformedException("Point must be between A-J and 1-10");
            }
            return new Point(x, y);
        } catch (NumberFormatException ex) {
            throw new MalformedException("Malformed number");
        }
    }
}

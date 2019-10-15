package battleship.point;

import org.apache.commons.lang3.StringUtils;

import battleship.exception.MalformedException;

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
            return new Point.Builder(x, y).build();
        } catch (NumberFormatException ex) {
            throw new MalformedException("Malformed number");
        }
    }

    public static int getRow(String pointString) throws MalformedException {
        if (StringUtils.isEmpty(pointString)) {
            throw new MalformedException("Point cannot be empty");
        }
        String posX = pointString.substring(1);
        try {
            int x = Integer.parseInt(posX) - 1;
            return x;
        } catch (NumberFormatException e) {
            throw new MalformedException("You have to set Row position");
        }
    }

    public static int getColumn(String pointString) throws MalformedException {
        if (StringUtils.isEmpty(pointString)) {
            throw new MalformedException("Point cannot be empty");
        }
        pointString = pointString.toLowerCase();
        char posY = pointString.charAt(0);
        int y = (int) posY - (int) 'a';
        return y;
    }

}

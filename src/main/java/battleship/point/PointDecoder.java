package battleship.point;

import org.apache.commons.lang3.StringUtils;

import battleship.exception.MalformattedException;

public class PointDecoder {
    public static PointImpl inputToPoint(String input) throws MalformattedException {
        if (StringUtils.isEmpty(input)) {
            throw new MalformattedException("Point cannot be empty");
        }
        input = input.toLowerCase();
        char posY = input.charAt(0);
        String posX = input.substring(1);

        int y = (int) posY - (int) 'a';
        try {
            int x = Integer.parseInt(posX) - 1;
            return new PointImpl.Builder(x, y).build();
        } catch (NumberFormatException ex) {
            throw new MalformattedException("Malformed number");
        }
    }

    public static int getRow(String pointString) throws MalformattedException {
        if (StringUtils.isEmpty(pointString)) {
            throw new MalformattedException("Point cannot be empty");
        }
        String posX = pointString.substring(1);
        try {
            int x = Integer.parseInt(posX) - 1;
            return x;
        } catch (NumberFormatException e) {
            throw new MalformattedException("You have to set Row position");
        }
    }

    public static int getColumn(String pointString) throws MalformattedException {
        if (StringUtils.isEmpty(pointString)) {
            throw new MalformattedException("Point cannot be empty");
        }
        pointString = pointString.toLowerCase();
        char posY = pointString.charAt(0);
        int y = (int) posY - (int) 'a';
        return y;
    }

}

package battleship.v1.point;

public class PointDecoder {
    public static Point inputToPoint(String input) {        
        input = input.toLowerCase();
        char posY = input.charAt(0);
        String posX = input.substring(1);

        int y = (int) posY - (int) 'a';
        int x = Integer.parseInt(posX) - 1;         
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            throw new IllegalArgumentException("Ivalid position value");
        }
        return new PointImpl(x,y);
    }

    /**public static Direction inputToDirection(String input) {
        char posDirection = input.toLowerCase()
                .charAt(0);
        if (!charToShipClass.containsKey(posDirection)) {
            throw new IllegalArgumentException("Invalid direction value");
        }
        return charToShipClass.get(posDirection);

    }*/
    public static void main(String[] args) {
        //System.out.println(inputToPoint("J10"));
        //System.out.println((int)'a');
    }
}

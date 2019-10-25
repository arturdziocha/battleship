package battleship.view;

import java.util.Scanner;

public class ConsoleReader {
    private Scanner scanner = new Scanner(System.in);
    public ConsoleReader() {
        scanner = new Scanner(System.in);
    }

    public String shotPointReader() {
        System.out.println("Please enter a point to shot like 'B1' Column must be between A-J, row between 1-10");
        System.out.println(">>>");
        return scanner.next();
    }
}

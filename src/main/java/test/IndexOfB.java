package test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IndexOfB {
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(9, 8, 7, 6, 5);
        
        Scanner reader = new Scanner(System.in);
        
        boolean success = false;
        while(success == false) {
            success = true;
            try {
                System.out.println("A number");
                int num = reader.nextInt();  
                System.out.println(ints.get(num));
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getStackTrace());
                success = false;
            }
        }        

    }
}

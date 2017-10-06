import java.util.Scanner;

public class mult {


public static void main(String[] args) {
  int a = Long.parseLong(args[0]);
  int b = Long.parseLong(args[1]);
  System.out.println(multiRec(a,b));
}

private static int c = 0; 
public static int multiRec(int x, int y) {
    if (x == 0 || y == 0) {
        return 0;
    } else {
        if (x == 1) {
            return y;
        } else {
            c = x + (multiRec(x, y - 1));
            return c;
        }
    }

  }

}
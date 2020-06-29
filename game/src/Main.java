import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String format = s.nextLine();
        ArrayTiles mt = new ArrayTiles(format);
        mt.play();
    }
}

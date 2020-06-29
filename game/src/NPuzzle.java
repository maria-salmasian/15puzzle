import java.util.Scanner;

public class NPuzzle {
    private Tiles tiles;

    public NPuzzle(Tiles t) {
        this.tiles = t;
    }

    public void play() {
        System.out.println("Please enter the Char");
        Scanner enter = new Scanner(System.in);
        String k = enter.next();
        while (!k.equals("q")) {
            Direction direction = Direction.valueOf(k);
            tiles.move(direction);
            print();
            k = enter.next();

        }
        tiles.isSolved();
    }


    public void print() {
        if (tiles instanceof MatrixTiles) {
            System.out.println(tiles.getMoveCount());
            for (int i = 0; i < Tiles.SIZE; i++) {
                for (int j = 0; j < Tiles.SIZE; j++) {
                    System.out.print(tiles.getTile(i, j) + "\t");
                }
                System.out.println();

            }
        } else if (tiles instanceof ArrayTiles) {
            System.out.println(tiles.getMoveCount());
            for (int i = 0; i < Tiles.SIZE * Tiles.SIZE; i++) {
                System.out.print(tiles.getTile(0, i) + "\t");
                if (i == 3 || i == 7 || i == 11 || i == 15)
                    System.out.print("\n");
            }
            System.out.println();
            System.out.println("Enter Char");
        }
    }

    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            String format = s.nextLine();
            Tiles t = new ArrayTiles(format);
            if (!t.isSolvable()) {
                System.out.println("The game is not solvable. Quitting.");
                System.exit(0);
            }

            NPuzzle nPuzzle = new NPuzzle(t);
            nPuzzle.play();
        } catch (ConfigurationFormatException e ) {
            System.out.println(e.getMessage());
        } catch (InvalidConfiguationException e){
            System.out.println(e.getMessage());
        }
    }
}

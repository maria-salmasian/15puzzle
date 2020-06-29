import java.util.Scanner;

public abstract class Tiles {
    static final int SIZE = 4;
    static final int EMPTY = 0;
    private int moves = 0;
    private Configuration configuration;

    Tiles(String format) {
        configuration = new Configuration(format);
    }

    public int getMoveCount() {
        return moves;
    }

    protected void incrementMoveCount() {
        moves++;
    }

    protected Configuration getConfiguration() {
        return configuration;
    }

    public void move(Direction direction) {
        moveImpl(direction);
    }

    protected abstract void moveImpl(Direction direction);

    public abstract byte getTile(int col, int row);

    public abstract void setTile(int col, int row, byte value);

    public abstract boolean isSolved();

    public abstract void print();

    public void play() {
        System.out.println("Please enter the Char");
        Scanner enter = new Scanner(System.in);
        String k = enter.next();
        while (!k.equals("q")) {
            Direction direction = Direction.valueOf(k);
            move(direction);
            print();
            k = enter.next();

        }
        isSolved();


    }

    public void ensureValidity() {
        if (this instanceof ArrayTiles) {
            ensureValidity(SIZE * SIZE);
        } else {
            ensureValidity(SIZE, SIZE);
        }

    }

    public boolean isSolvable() {
        int wrong = 0;
        int thisValue;
        for (int i = 0; i < SIZE * SIZE - 2; i++) {
            thisValue = this.getTile(i / SIZE, i % SIZE);
            for (int j = i + 1; j < SIZE * SIZE - 1; j++) {
                if (thisValue > this.getTile(j / SIZE, j % SIZE))
                    wrong++;
            }
        }
        return wrong % 2 != 1;
    }

    private void ensureValidity(int size) {
        int sum = 0;
        int[] count = new int[size];
        for (int i = 0; i < size; i++) {
            if (getTile(0, i) >= size) {
                throw new InvalidConfiguationException(String.format("Invalid configuration: incorrect tile value %s.", getTile(0, i)));
            }
            if (getTile(0, i) == 0) {
                sum++;
                if (sum > 1) {
                    throw new InvalidConfiguationException("Invalid configuration: multiple empty spaces.");
                }
            }
            if (count[getTile(0, i)] == 1) {
                throw new InvalidConfiguationException(String.format("Invalid configuration: multiple tiles with the value %s.", getTile(0, i)));
            } else {
                count[getTile(0, i)]++;
            }
        }
    }

    private void ensureValidity(int width, int legth) {
        int sum = 0;
        int[] count = new int[width * legth];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < legth; j++) {
                if (getTile(i, j) >= width * legth) {
                    throw new InvalidConfiguationException(String.format("Invalid configuration: incorrect tile value %s.", getTile(i, j)));
                }
                if (getTile(i, j) == 0) {
                    sum++;
                    if (sum > 1) {
                        throw new InvalidConfiguationException("Invalid configuration: multiple empty spaces.");
                    }
                }
                if (count[getTile(i, j)] == 1) {
                    throw new InvalidConfiguationException(String.format("Invalid configuration: multiple tiles with the value %s.", getTile(i, j)));

                } else {
                    count[getTile(i, j)]++;
                }
            }
        }
    }
}

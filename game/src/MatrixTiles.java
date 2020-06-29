public class MatrixTiles extends Tiles {

    private byte[][] tiles;
    private int emptyCol;
    private int emptyRow;
    private int[][] done = new int[4][4];
    private int initial = 1;

    MatrixTiles(String format) {
        super(format);
        tiles = new byte[SIZE][SIZE];
        try {
            getConfiguration().initialise(this);
        }
        catch (ConfigurationFormatException e){
            throw new ConfigurationFormatException(e.getMessage());
        }catch (InvalidConfiguationException e){
            throw new InvalidConfiguationException(e.getMessage());
        }
        getEmpty();
    }

    private int[][] getDone() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.done[i][j] = initial;
                initial++;
                if (initial == 16)
                    break;
            }
        }
        return this.done;
    }

    protected void moveImpl(Direction direction) {
        if (direction == Direction.DOWN) {
            getEmpty();
            byte d = getTile(emptyCol + 1, emptyRow);
            setTile(emptyCol + 1, emptyRow, (byte) EMPTY);
            setTile(emptyCol, emptyRow, d);
            incrementMoveCount();
        }

        if (direction == Direction.UP) {
            getEmpty();
            byte u = getTile(emptyCol - 1, emptyRow);
            setTile(emptyCol - 1, emptyRow, (byte) EMPTY);
            setTile(emptyCol, emptyRow, u);
            incrementMoveCount();
        }

        if (direction == Direction.RIGHT) {
            getEmpty();
            byte r = getTile(emptyCol, emptyRow + 1);
            setTile(emptyCol, emptyRow + 1, (byte) EMPTY);
            setTile(emptyCol, emptyRow, r);

        }

        if (direction == Direction.LEFT) {
            getEmpty();
            byte l = getTile(emptyCol, emptyRow - 1);
            setTile(emptyCol, emptyRow - 1, (byte) EMPTY);
            setTile(emptyCol, emptyRow, l);
            incrementMoveCount();
        }
    }

    public byte getTile(int col, int row) {
        if (row < 0 || row >= SIZE) {
            System.out.println("Error: position out of the board");
            System.exit(0);
        }
        if (col < 0 || col >= SIZE) {
            System.out.println("Error: position out of the board");
            System.exit(0);
        }
        return tiles[col][row];
    }

    public void setTile(int col, int row, byte value) {
        tiles[col][row] = value;
    }

    public boolean isSolved() {
        getDone();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j] != done[i][j]) {
                    System.out.println("Nah...try again!");
                    return false;
                }
            }

        }
        System.out.println("good job!");
        return true;
    }

    public void print() {
        System.out.println(getMoveCount());
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(tiles[i][j] + "\t");
            }
            System.out.println();

        }
    }

    private void getEmpty() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j] == EMPTY) {
                    emptyCol = i;
                    emptyRow = j;
                }
            }
        }
    }
}

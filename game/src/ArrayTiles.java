public class ArrayTiles extends Tiles {

    private byte[] tile;
    private int emptyPos;

    ArrayTiles(String format) {
        super(format);
        tile = new byte[SIZE*SIZE];
        try{
            getConfiguration().initialise(this);

        } catch (ConfigurationFormatException e){
            throw new ConfigurationFormatException(e.getMessage());
        }catch (InvalidConfiguationException e){
            throw new InvalidConfiguationException(e.getMessage());
        }
        getEmpty();
    }


    protected void moveImpl(Direction direction) {
        if (direction == Direction.DOWN) {
            getEmpty();
            byte d = getTile(0, (byte) (emptyPos + 4));
            setTile(0, (emptyPos + 4), (byte) EMPTY);
            setTile(0, emptyPos , d);
            incrementMoveCount();
        }

        if (direction == Direction.UP) {
            getEmpty();
            byte u = getTile(0, (byte) (emptyPos - 4));
            setTile(0,(byte) (emptyPos - 4), (byte) EMPTY);
            setTile(0,emptyPos, u);
            incrementMoveCount();
        }

        if (direction == Direction.RIGHT) {
            getEmpty();
            byte r = getTile(0, (byte) (emptyPos + 1));
            setTile(0, (emptyPos + 1), (byte) EMPTY);
            setTile(0, emptyPos, r);
            incrementMoveCount();
        }

        if (direction == Direction.LEFT) {
            getEmpty();
            byte l = getTile(0, (byte) (emptyPos - 1));
            setTile(0, (emptyPos - 1), (byte) EMPTY);
            setTile(0, emptyPos, l);
            incrementMoveCount();
        }
    }

    public byte getTile(int row, int col) {
        if (col < 0 || col >= SIZE * SIZE) {
            System.out.println("Error: position out of the board");
            System.exit(0);
        }
        return tile[col];
    }

    public void setTile( int col, int row, byte value) {
        tile[row] = value;
    }

    public boolean isSolved() {
        for (int i = (SIZE * SIZE) - 2; i >= 0; i--) {
            if (tile[i] != i + 1 || tile[(SIZE * SIZE) - 1] != EMPTY) {
                System.out.println("Nah...Sorry, you have to try again");
                return false;
            }
        }
        System.out.println("good job!");
        return true;
    }

    public void print() {
        System.out.println(getMoveCount());
        for (int i = 0; i < SIZE * SIZE; i++) {
            System.out.print(tile[i] + "\t");
            if (i == 3 || i == 7 || i == 11 || i == 15)
                System.out.print("\n");
        }
        System.out.println();
        System.out.println("Enter Char");
    }

    private void getEmpty() {
        for (int i = 0; i < SIZE*SIZE; i++) {
            if (tile[i] == EMPTY) {
                emptyPos = i;
            }
        }
    }

}

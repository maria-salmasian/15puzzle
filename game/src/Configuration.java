import java.util.Arrays;

public class Configuration {

    private String data;

    public Configuration(String format) {
        if (!format.equals("")) {
            this.data = format;
        } else {
            throw new ConfigurationFormatException("Please specify a configuration.");
        }
    }

    String getData() {
        return data;
    }

    public void initialise(Tiles tiles) {

        String[] format = getData().split(":");
        if (format.length < Tiles.SIZE) {
            throw new ConfigurationFormatException("Invalid configuration format: Incorrect number of rows in\n" +
                    "configuration (found 3).");
        }
        String[] format1 = Arrays.toString(format).split(" ");
        String k = Arrays.toString(format1).replace(":", "").replace(",", "").replace("[",
                "").replace("]", "").replace("   ", " ");
        String[] k1 = k.split(" ");
        if (!getData().contains(":")) {
            throw new ConfigurationFormatException("Invalid configuration format:  Incorrect number of rows in configuration (found 1).");
        }
        if (k1.length < Tiles.SIZE * Tiles.SIZE) {
            throw new ConfigurationFormatException("Invalid configuration format: Incorrect number of columns\n" +
                    "in configuration (found 3).");
        }

        int o = 0;
        if (tiles instanceof MatrixTiles) {
            while (o < Tiles.SIZE * Tiles.SIZE) {
                for (int i = 0; i < Tiles.SIZE; i++) {
                    for (int j = 0; j < Tiles.SIZE; j++) {
                        try {
                            tiles.setTile(i, j, Byte.parseByte(k1[o]));
                        } catch (Exception e) {
                            throw new ConfigurationFormatException(String.format("Invalid configuration format: Malformed configuration '%s'", data));
                        }
                        System.out.print(tiles.getTile(i, j) + "\t");
                        o++;
                    }
                    System.out.println();
                }
            }
        } else if (tiles instanceof ArrayTiles) {
            while (o < Tiles.SIZE * Tiles.SIZE) {
                for (int i = 0; i < Tiles.SIZE * Tiles.SIZE; i++) {
                    try {
                        tiles.setTile(0, i, Byte.parseByte(k1[o]));
                    } catch (Exception e) {
                        throw new ConfigurationFormatException(String.format("Invalid configuration format: Malformed configuration '%s'", data));
                    }
                    System.out.print(tiles.getTile(0, i) + "\t");
                    if (i == 3 || i == 7 || i == 11 || i == 15)
                        System.out.print("\n");
                    o++;

                }
            }
        }
        try{
            tiles.ensureValidity();
        } catch (InvalidConfiguationException e){
            throw new InvalidConfiguationException(e.getMessage());
        }

    }
}

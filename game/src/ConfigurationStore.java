import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class ConfigurationStore {

    public static final int MAX_NUMBER_CONFIGS = 1000;
    private Configuration[] configs = new Configuration[MAX_NUMBER_CONFIGS];
    private int numberUsed = 0;

    public ConfigurationStore(String source) throws IOException {
        if (source.startsWith("http://") || source.startsWith("https://")) {
            loadFromURL(source);
        } else {
            loadFromDisk(source);
        }
    }

    public ConfigurationStore(Reader source) throws IOException {
        load(source);
    }

    private void load(Reader r) throws IOException {
        String line;
        while ((line =((BufferedReader)r).readLine()) != null) {
            System.out.println(numberUsed);
            configs[numberUsed] = new Configuration(line);
            numberUsed++;
        }
        System.out.println("Final number used:" + numberUsed);
        for (int i = 0; i < configs.length; i++) {
            if (configs[i] != null){
                System.out.println(configs[i].getData());
            }
        }
    }

    private void loadFromURL(String url) throws IOException {
        URL destination = new URL(url);
        URLConnection conn = destination.openConnection();
        Reader r = new InputStreamReader(conn.getInputStream());
        BufferedReader b = new BufferedReader(r);
        load(b);
    }

    private void loadFromDisk(String filename) throws IOException {
        Reader r = new FileReader(filename);
        BufferedReader b = new BufferedReader(r);
        load(b);
    }

    public static void main(String[] args) throws IOException {
        try {
            ConfigurationStore p = new ConfigurationStore("https://bit.ly/3dL0iGa");

        } catch (IOException e){
            System.out.println("IOException occured");
        }
        //ConfigurationStore p = new ConfigurationStore("/home/maria/Desktop/config.txt");
    }
}

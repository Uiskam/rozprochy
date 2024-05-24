import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private final Properties prop = new Properties();

    Config() throws IOException {
        String configFilePath = "src/main/java/config.properties";
        FileInputStream propsInput = new FileInputStream(configFilePath);
        prop.load(propsInput);
    }

    public String getProperty(String key) {
        return this.prop.getProperty(key);
    }
}

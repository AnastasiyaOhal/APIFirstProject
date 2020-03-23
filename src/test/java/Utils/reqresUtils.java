package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class reqresUtils {

    public static String getAppJson() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("configuration.properties")));
        return properties.getProperty("applicationJson");
    }

    public static String generateStringResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}

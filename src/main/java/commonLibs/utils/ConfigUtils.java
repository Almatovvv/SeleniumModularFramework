package commonLibs.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    public static Properties readProperties(String fileName) throws IOException {
        fileName = fileName.trim();
        InputStream fileReader = new FileInputStream(fileName);
        Properties property = new Properties();
        property.load(fileReader);
        return property;
    }
}

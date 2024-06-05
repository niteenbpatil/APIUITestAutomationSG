package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigFile {
    private static String environment = System.getProperty("environment");
    private static String propertyFilePath = "src/main/resources/properties/" + environment + ".properties";

    public static String readConfig(String property) {
        String propValue = null;
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(propertyFilePath);
            prop.load(fis);
            propValue = prop.getProperty(property);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propValue;

    }


}


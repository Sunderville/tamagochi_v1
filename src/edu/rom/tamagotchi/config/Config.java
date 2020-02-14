package edu.rom.tamagotchi.config;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static Properties properties = new Properties();

    public static String getProp(String propValue) {
        if (properties.isEmpty()) {
            try (InputStream is = Config.class.getClassLoader()
                    .getResourceAsStream("Pet_settings.properties")) {
                if (is != null) {
                    properties.load(is);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
        return properties.getProperty(propValue);
    }
}
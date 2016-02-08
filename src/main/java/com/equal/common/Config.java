package com.equal.common;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Reporter;

public class Config {
    public static Properties config;

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static String getProperty(String key) {
        return getProperty(key, null);
    }

    public static String getProperty(String key, String defaultValue) {
        String propertyValue = defaultValue;
        try {
            String fileName = "project.properties";
            if (config == null) {
                config = loadProperties(fileName);
            }
            if (config.containsKey(key)) {
                propertyValue = config.getProperty(key);
            }
        } catch (Exception e) {

            Reporter.log("Error while reading config: " + e.getMessage(), 2,
                    true);
        }
        return propertyValue;
    }

    public static Properties loadProperties(String path) throws Exception {
        Properties result = new Properties();
        InputStream in = Config.class.getClassLoader()
                .getResourceAsStream(path);
        if (in == null) {
            in = new FileInputStream(path);
        }
        result.load(in);
        return result;
    }
}

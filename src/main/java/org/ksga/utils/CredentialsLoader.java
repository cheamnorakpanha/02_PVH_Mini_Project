package org.ksga.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CredentialsLoader {

    private static final String FILE_NAME = "application.properties";

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties file", e);
        }
        return properties;
    }
}

package org.ksga.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CredentialsLoader {

    private static final String FILE_NAME = "application.properties";

    public static Properties loadProperties() {
        Properties properties = new Properties();

        // Use ClassLoader to find the file in the classpath
        try (InputStream input = CredentialsLoader.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + FILE_NAME + " in the classpath.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties file", e);
        }

        return properties;
    }
}
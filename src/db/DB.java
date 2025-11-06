package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DB {
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(fs);
            return properties;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}

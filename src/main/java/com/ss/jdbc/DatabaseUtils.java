package com.ss.jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Provides a small Utility Kit for reading the database-config
 * and establishing a new database connection instance.
 * 
 * @author Joshua Tyler
 */
public class DatabaseUtils {

  public Connection getConnection()
    throws ClassNotFoundException, IOException, SQLException {

		Properties config = getConfig();
		final String driver = config.getProperty("driver");
		final String url = config.getProperty("url");
		final String username = config.getProperty("username");
    final String password = config.getProperty("password");
    
    Class.forName(driver);
    Connection connection = DriverManager.getConnection(url, username, password);
    connection.setAutoCommit(Boolean.FALSE);
    return connection;
  }

  private Properties getConfig() throws IOException {
    final String configName = "database-config.properties";
    Properties props = new Properties();
    InputStream inputStream = getClass()
      .getClassLoader()
      .getResourceAsStream(configName);

    if (inputStream == null) {
      throw new FileNotFoundException(
        "Unable to load database-config, file (" + configName + ") not found!"
      );
    }

    props.load(inputStream);
    return props;
  }
}

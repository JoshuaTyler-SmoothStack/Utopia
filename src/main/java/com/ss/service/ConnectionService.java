package com.ss.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.ss.KitUtils;

public class ConnectionService {
  volatile private static Connection ReadOnlyConnection = null;
  private Connection getReadOnlyConnection() throws ClassNotFoundException, IOException, SQLException {
    synchronized(ReadOnlyConnection) {
      if(ReadOnlyConnection == null) {
        ReadOnlyConnection = new ConnectionService().createNewConnection();
      }
    }
    return ReadOnlyConnection;
  }

  public Connection getConnection(Boolean isReadOnly) throws ClassNotFoundException, IOException, SQLException {
    if(isReadOnly) {
      // @TODO: status check the ReadOnly Connection, reseting if needed
      return getReadOnlyConnection();
    } else {
      // @TODO: connection pool for writing
      return createNewConnection();
    }
  }

  private Connection createNewConnection() {
    //throws ClassNotFoundException, IOException, SQLException {
      try {
        Properties config = getConfig();
        final String driver = config.getProperty("driver");
        final String url = config.getProperty("url");
        final String username = config.getProperty("username");
        final String password = config.getProperty("password");
        
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(Boolean.FALSE);
        return connection;
      } catch(ClassNotFoundException e) {
        System.out.println(KitUtils.ANSI_TEST_FAIL + "ClassNotFound" + KitUtils.ANSI_RESET);
      } catch(IOException e) {
        System.out.println(KitUtils.ANSI_TEST_FAIL + "IO" + KitUtils.ANSI_RESET);
      } catch(SQLException e) {
        System.out.println(KitUtils.ANSI_TEST_FAIL + "SQL" + KitUtils.ANSI_RESET);
      }
      return null;
  }

  private Properties getConfig() throws IOException {
    final String configName = "static/database.properties";
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

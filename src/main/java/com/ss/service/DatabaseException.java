package com.ss.service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides a custom Exception for alerting
 * the UI tier when a Database operations has failed
 *
 * @author Joshua Tyler
 */
// prettier-ignore
public class DatabaseException extends Exception {

  private static final long serialVersionUID = 2642505182560606786L;

  
  public DatabaseException(Boolean tryRollback, Connection connection, Exception e) {
    super(createNewException(tryRollback, connection, e));
  }

  
  private static String createNewException(Boolean tryRollback, Connection connection, Exception e) {
    if (tryRollback) {
      try {
        connection.rollback();
        return DatabaseException.getDatabaseExceptionMessage(connection, e, true);

      } catch (SQLException rollbackError) {
        return DatabaseException.getDatabaseExceptionMessage(connection, e, false);
      }
    }
    return DatabaseException.getDatabaseExceptionMessage(connection, e);
  }

  
  private static String getDatabaseExceptionMessage(Connection connection, Exception e) {
    return  (connection != null)
    ? "[ERROR] Database: Connection has failed!\n" + 
    "Connection: " + connection + "\n" 
    + "Error: " + e
    : "[ERROR] Database: Unable to establish Database connection!\n" + e;
  }

  
  private static String getDatabaseExceptionMessage(Connection connection, Exception e, Boolean isRollbackSuccessful) {
    String rollbackMessage = isRollbackSuccessful
      ? "Rolled back changes successfully."
      : "Unable to rollback changes!";
    return  (connection != null)
    ? "[ERROR] Database: Connection has failed!\n" + 
    rollbackMessage + "\n" +
    "Connection: " + connection + "\n" 
    + "Error: " + e
    : "[ERROR] Database: Unable to establish Database connection!\n" + e;
  }
}

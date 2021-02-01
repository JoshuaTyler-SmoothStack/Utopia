package com.ss.jdbc;

import java.sql.Connection;

import com.ss.KitUtils;

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

      } catch (Exception rollbackError) {
        return DatabaseException.getDatabaseExceptionMessage(connection, rollbackError, false);
      }
    }
    return DatabaseException.getDatabaseExceptionMessage(connection, e);
  }

  
  private static String getDatabaseExceptionMessage(Connection connection, Exception e) {
    return  (connection != null)
    ? "\n" + KitUtils.ANSI_TEST_FAIL + "[ERROR] Database: Connection has failed!" + 
    "\nConnection: " + connection + 
    "\nError: " + e + KitUtils.ANSI_RESET
    : "\n" + KitUtils.ANSI_TEST_FAIL + "[ERROR] Database: Unable to establish Database connection!\n" + e + KitUtils.ANSI_RESET;
  }

  
  private static String getDatabaseExceptionMessage(Connection connection, Exception e, Boolean isRollbackSuccessful) {
    String rollbackMessage = isRollbackSuccessful
      ? "Rolled back changes successfully."
      : "Unable to rollback changes!";
    return  (connection != null)
    ? "\n" + KitUtils.ANSI_TEST_FAIL + "[ERROR] Database: Connection has failed!" + 
    "\n" + rollbackMessage + 
    "\nConnection: " + connection +  
    "\nError: " + e  + KitUtils.ANSI_RESET
    : "\n" + KitUtils.ANSI_TEST_FAIL + "[ERROR] Database: Unable to establish Database connection!\n" + e + KitUtils.ANSI_RESET;
  }
}

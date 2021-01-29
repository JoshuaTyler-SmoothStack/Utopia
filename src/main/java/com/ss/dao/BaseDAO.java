/**
 *
 */
package com.ss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Joshua Tyler
 *
 */
public abstract class BaseDAO<T> {

  protected static Connection connection = null;

  public BaseDAO(Connection connection) {
    BaseDAO.connection = connection;
  }

  abstract List<T> extractData(ResultSet rs)
    throws SQLException, ClassNotFoundException;

  private static PreparedStatement formatStatement(String sql, Object[] vals)
    throws SQLException, ClassNotFoundException {
    PreparedStatement prepStatement = connection.prepareStatement(sql);
    if (vals != null) {
      int index = 1;
      for (Object o : vals) {
        prepStatement.setObject(index, o);
        index++;
      }
    }
    return prepStatement;
  }

  public List<T> read(String sql, Object[] vals)
    throws SQLException, ClassNotFoundException {
    PreparedStatement prepStatement = formatStatement(sql, vals);
    ResultSet rs = prepStatement.executeQuery();
    return extractData(rs);
  }

  public void save(String sql, Object[] vals)
    throws SQLException, ClassNotFoundException {
    PreparedStatement prepStatement = formatStatement(sql, vals);
    prepStatement.execute();
  }
}

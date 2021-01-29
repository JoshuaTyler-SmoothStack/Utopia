package com.ss.jdbc;

import com.ss.dao.AirplaneDAO;
import com.ss.entity.Airplane;
import java.sql.Connection;
import java.util.List;

/**
 * @author Joshua Tyler
 *
 */
public class AirplaneDBC {

  /**
   * @param args
   */
  public static void main(String[] args) throws DatabaseException {
    // AirplaneDBC airlines = new AirplaneDBC();
    // airlines.saveAirplane();
    // airlines.getAllAirplanesById();
    // airlines.getAllAirplanes();
  }

  public static List<Airplane> getAllAirplanes() throws DatabaseException {
    Connection connection = null;
    try {
      connection = new DatabaseUtils().getConnection();
      return new AirplaneDAO(connection).getAllAirplanes();
    } catch (Exception e) {
      throw new DatabaseException(false, connection, e);
    } finally {
      try {
        connection.close();
      } catch(Exception e){}
    }
  }

  public static List<Airplane> getAllAirplanesById(Integer id) throws DatabaseException {
    Connection connection = null;
    try {
      connection = new DatabaseUtils().getConnection();
      return new AirplaneDAO(connection).getAllAirplanesById(id);
    } catch (Exception e) {
      throw new DatabaseException(false, connection, e);
    } finally {
      try {
        connection.close();
      } catch(Exception e){}
    }
  }

  public static void saveAirplane(Airplane airplane) throws DatabaseException {
    Connection connection = null;
    try {
      connection = new DatabaseUtils().getConnection();
      new AirplaneDAO(connection).createAirplane(airplane);
    } catch (Exception e) {
      throw new DatabaseException(true, connection, e);
    } finally {
      try {
        connection.close();
      } catch(Exception e){}
    }
  }
}

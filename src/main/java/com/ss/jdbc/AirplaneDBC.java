package com.ss.jdbc;

import com.ss.dao.AirplaneDAO;
import com.ss.dao.AirplaneTypeDAO;
import com.ss.entity.Airplane;
import com.ss.entity.AirplaneType;
import com.ss.service.ConnectionService;

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
  public static void main(String[] args) {
    //Airplane newAirplane = new Airplane(1, new AirplaneType(1, 150));
    Connection connection = null;
    try {
      connection = new ConnectionService().getConnection(false);
      new AirplaneTypeDAO(connection).createAirplaneType(new AirplaneType(4, 900));
      connection.commit();
      connection.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static List<Airplane> getAllAirplanes() throws DatabaseException {
    Connection connection = null;
    try {
      connection = new ConnectionService().getConnection(true);
      return new AirplaneDAO(connection).getAllAirplanes();
    } catch (Exception e) {
      throw new DatabaseException(false, connection, e);
    }
  }

  public static List<Airplane> getAllAirplanesById(Integer id) throws DatabaseException {
    Connection connection = null;
    try {
      connection = new ConnectionService().getConnection(true);
      return new AirplaneDAO(connection).getAllAirplanesById(id);
    } catch (Exception e) {
      throw new DatabaseException(false, connection, e);
    }
  }

  public static void saveAirplane(Airplane airplane) throws DatabaseException {
    Connection connection = null;
    try {
      connection = new ConnectionService().getConnection(false);
      new AirplaneDAO(connection).createAirplane(airplane);
    } catch (Exception e) {
      throw new DatabaseException(true, connection, e);
    } finally {
      // @TODO: ConnectionService.poolConnection(connection)
      try{
        connection.close();
      }catch(Exception e){}
    }
  }
}
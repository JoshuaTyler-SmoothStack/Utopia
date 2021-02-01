package com.ss.dao;

import com.ss.entity.AirplaneType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joshua Tyler
 *
 */
public class AirplaneTypeDAO extends BaseDAO<AirplaneType> {

  public AirplaneTypeDAO(Connection connection) {
    super(connection);
  }

  public void createAirplaneType(AirplaneType airplaneType)
    throws ClassNotFoundException, SQLException {
    save(
      "insert into airplane_type (max_capacity) values (?)",
      new Object[] { airplaneType.getMaxCapacity() }
    );
  }

  public void updateAirplaneType(AirplaneType airplaneType)
    throws ClassNotFoundException, SQLException {
    save(
      "update airplane_type set WHERE id = ? max_capacity = ? ",
      new Object[] { airplaneType.getId(), airplaneType.getMaxCapacity() }
    );
  }

  public void deleteAirplaneType(AirplaneType airplaneType)
    throws ClassNotFoundException, SQLException {
    save(
      "delete from airplane_type id = ?",
      new Object[] { airplaneType.getId() }
    );
  }

  public AirplaneType getAirplaneType(Integer id)
    throws ClassNotFoundException, SQLException {
    return read(
      "SELECT * FROM airplane_type WHERE id = ?",
      new Object[] { id }
    ).get(0);
  }

  public List<AirplaneType> getAllAirplaneTypes()
    throws ClassNotFoundException, SQLException {
    return read("SELECT * FROM airplane_type", null);
  }

  public List<AirplaneType> getAllAirplaneTypesByEqualMaxCapacity(Integer maxCapacity)
    throws ClassNotFoundException, SQLException {
    return read(
      "SELECT * FROM airplane_type WHERE max_capacity = ?",
      new Object[] { maxCapacity }
    );
  }

  public List<AirplaneType> getAllAirplaneTypesByGreaterThanMaxCapacity(Integer maxCapacity)
    throws ClassNotFoundException, SQLException {
    return read(
      "SELECT * FROM airplane_type WHERE max_capacity > ?",
      new Object[] { maxCapacity }
    );
  }

  public List<AirplaneType> getAllAirplaneTypesByGreaterThanOrEqualToMaxCapacity(Integer maxCapacity)
    throws ClassNotFoundException, SQLException {
    return read(
      "SELECT * FROM airplane_type WHERE max_capacity >= ?",
      new Object[] { maxCapacity }
    );
  }

  public List<AirplaneType> getAllAirplaneTypesByLessThanMaxCapacity(Integer maxCapacity)
    throws ClassNotFoundException, SQLException {
    return read(
      "SELECT * FROM airplane_type WHERE max_capacity < ?",
      new Object[] { maxCapacity }
    );
  }

  public List<AirplaneType> getAllAirplaneTypesByLessThanOrEqualToMaxCapacity(Integer maxCapacity)
    throws ClassNotFoundException, SQLException {
    return read(
      "SELECT * FROM airplane_type WHERE max_capacity <= ?",
      new Object[] { maxCapacity }
    );
  }

  @Override
  List<AirplaneType> extractData(ResultSet rs)
    throws SQLException, ClassNotFoundException {
    List<AirplaneType> airplanesTypes = new ArrayList<>();
    while (rs.next()) {
      AirplaneType airplaneType = new AirplaneType(
        rs.getInt("id"),
        rs.getInt("max_capacity")
      );
      airplanesTypes.add(airplaneType);
    }
    return airplanesTypes;
  }
}

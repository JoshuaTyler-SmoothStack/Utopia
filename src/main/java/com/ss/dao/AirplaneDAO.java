package com.ss.dao;

import com.ss.KitUtils;
import com.ss.entity.Airplane;
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
public class AirplaneDAO extends BaseDAO<Airplane> {

  public AirplaneDAO(Connection connection) {
    super(connection);
  }

  public void createAirplane(Airplane airplane)
    throws ClassNotFoundException, SQLException {
      System.out.println(KitUtils.ANSI_TEST_PASS + airplane.getAirplaneType().getId() + KitUtils.ANSI_RESET);
    save(
      "INSERT INTO airplane (type_id) VALUES (?)",
      new Object[] { airplane.getAirplaneType().getId() }
    );
  }

  public void updateAirplane(Airplane airplane)
    throws ClassNotFoundException, SQLException {
    save(
      "UPDATE airplane SET WHERE id = ? type_id = ? ",
      new Object[] { airplane.getId(), airplane.getAirplaneType().getId() }
    );
  }

  public void deleteAirplane(Airplane airplane)
    throws ClassNotFoundException, SQLException {
    save("DELETE FROM airplane id = ?", new Object[] { airplane.getId() });
  }

  public List<Airplane> getAllAirplanes()
    throws ClassNotFoundException, SQLException {
    return read("SELECT * FROM airplane", null);
  }

  public List<Airplane> getAllAirplanesById(Integer id)
    throws ClassNotFoundException, SQLException {
    return read(
      "SELECT * FROM airplane WHERE id = ?",
      new Object[] { id }
    );
  }

  public List<Airplane> getAllAirplanesByType(Integer typeId)
    throws ClassNotFoundException, SQLException {
    return read(
      "SELECT * FROM airplane WHERE type_id = ?",
      new Object[] { typeId }
    );
  }

  @Override
  List<Airplane> extractData(ResultSet rs)
    throws SQLException, ClassNotFoundException {
    List<Airplane> airplanes = new ArrayList<>();
    while (rs.next()) {
      AirplaneType type = new AirplaneType(rs.getInt("type_id"), null);
      Airplane airplane = new Airplane(rs.getInt("id"), type);
      airplanes.add(airplane);
    }
    return airplanes;
	}
}
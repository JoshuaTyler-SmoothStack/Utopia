package com.ss.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.ss.dao.AirplaneDAO;
import com.ss.entity.Airplane;

/**
 * @author Joshua Tyler
 *
 */
public class AirplaneService {

	private DatabaseUtils connUtil = new DatabaseUtils();

	public String saveAirplane(Airplane airplane) throws SQLException {
		Connection connection = null;
		try {
			connection = connUtil.getConnection();
			AirplaneDAO adao = new AirplaneDAO(connection);

			if (airplane.getAirplaneType().getId() != null && airplane.getId() != null) {
				adao.updateAirplane(airplane);
			} else if (airplane.getId() != null) {
				adao.deleteAirplane(airplane);
			} else {
				adao.createAirplane(airplane);
			}
			connection.commit(); //changes become permanent on db
			return "Airplane Saved sucessfully";
		} catch (Exception e) {
			connection.rollback();// revert and temp changes get eraased
			e.printStackTrace();
			return "Something went wrong - try again!!";
		} finally {
			if (connection != null)
				connection.close();
		}

	}

}

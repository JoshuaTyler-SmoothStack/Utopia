package com.ss.entity;

/**
 * @author Joshua Tyler
 *
 */
public class Airplane {
	
	private Integer id;
	private AirplaneType airplaneType;

	//region id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	//endregion

	//region airplaneType
	public AirplaneType getAirplaneType() {
		return airplaneType;
	}

	public void setAirplaneType(AirplaneType airplaneType) {
		this.airplaneType = airplaneType;
	}
	//endregion
}

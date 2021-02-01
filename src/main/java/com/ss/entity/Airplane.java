package com.ss.entity;

/**
 * @author Joshua Tyler
 *
 */
public class Airplane {
	
	private Integer id;
	private AirplaneType airplaneType;

	public Airplane(Integer id, AirplaneType airplaneType) {
		super();
		this.setId(id);
		this.setAirplaneType(airplaneType);
	}

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

	@Override
	public int hashCode() {
    final int prime = 32;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
	}

	@Override
	public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Airplane other = (Airplane) obj;
    if (id == null) {
      if (other.getId() != null) return false;
    } else if (!id.equals(other.getId())) return false;
    return true;
	}
}

package com.ss.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Joshua Tyler
 *
 */
public class AirplaneType implements Serializable {

  private static final long serialVersionUID = -4518915396094953540L;

  private Integer id;
  private Integer maxCapacity;
  private List<Airplane> airplanes;

  public AirplaneType(Integer id, Integer maxCapacity) {
    super();
    this.id = id;
    this.maxCapacity = maxCapacity;
  }

	//region Id
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
	}
	//endregion


	//region maxCapacity
  public Integer getmaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCap(Integer maxCapacity) {
    this.maxCapacity = maxCapacity;
	}
	//endregion


	// region airplances
  public List<Airplane> getAirplanes() {
    return airplanes;
  }

  public void setAirplanes(List<Airplane> airplanes) {
    this.airplanes = airplanes;
	}
	//endregion

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    AirplaneType other = (AirplaneType) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    return true;
  }
}

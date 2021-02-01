package com.ss.entity;

import java.io.Serializable;

/**
 * @author Joshua Tyler
 *
 */
public class AirplaneType implements Serializable {

  private static final long serialVersionUID = -4518915396094953540L;

  private Integer id;
  private Integer maxCapacity;

  public AirplaneType(Integer id, Integer maxCapacity) {
    super();
    this.setId(id);
    this.setMaxCap(maxCapacity);
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
  public Integer getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCap(Integer maxCapacity) {
    this.maxCapacity = maxCapacity;
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

package com.mx.base.models.entity;

import java.io.Serializable;


public class OrderPKEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long pk;

	private int year;

	public OrderPKEntity(){}
	
	public OrderPKEntity(long pk, int year){
		this.pk = pk;
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (pk ^ (pk >>> 32));
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderPKEntity other = (OrderPKEntity) obj;
		if (pk != other.pk)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderPKEntity [pk=" + pk + ", year=" + year + "]";
	}
	
}

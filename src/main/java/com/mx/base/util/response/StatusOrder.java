package com.mx.base.util.response;

public enum StatusOrder {
	
	NEW(1),
	RECEIVED(2),
	CLOSE(3),
	CANCEL(4);
	
	StatusOrder(int pk) {
		this.pk = pk;
	}

	private int pk;

	public int getpk() {
		return pk;
	}
	
	public static StatusOrder getStatus(int pk) {
		StatusOrder statusOrder = null;
		for(StatusOrder status : StatusOrder.values()){
			if(pk==status.getpk()){
				statusOrder = status;
			} 
		}
		return statusOrder;	
	}	

}

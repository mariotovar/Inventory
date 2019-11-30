package com.mx.base.util.response;

public enum ExpenseType {
	
	FREIGHT (1),
	TIPS (2),
	CUSTOMS (3),
	OTHERS (4);

	ExpenseType(int pk) {
		this.pk = pk;
	}

	private  int pk;

	public int getpk() {
		return pk;
	}

}

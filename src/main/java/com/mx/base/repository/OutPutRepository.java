package com.mx.base.repository;

public interface OutPutRepository {
	
	void saveOutPut(Object out);
	Object getInventoryOutPut(int year, Long pk);
	long getMaxId(int year);

}

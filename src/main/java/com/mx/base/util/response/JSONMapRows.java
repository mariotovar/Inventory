package com.mx.base.util.response;

import java.util.HashMap;
import java.util.Map;

public class JSONMapRows {
	
	private StatusResponse status;
	private Map<Long, String> mapRows;
		
	public JSONMapRows(){
		this.status = StatusResponse.SUCCESS;
		this.mapRows = new HashMap<Long, String>();
	}	

	public StatusResponse getStatus() {
		return status;
	}
	public void setStatus(StatusResponse status) {
		this.status = status;
	}
	public Map<Long, String> getMapRows() {
		return mapRows;
	}
	public void setMapRows(Map<Long, String> mapRows) {
		this.mapRows = mapRows;
	}

}

package com.mx.base.util.response;

import java.util.HashMap;
import java.util.Map;

public class JSONResponse {
	
	private StatusResponse status;
	private Map<String, String> errors;
		
	public JSONResponse(){
		this.status = StatusResponse.SUCCESS;
		this.errors = new HashMap<String, String>();
	}	

	public StatusResponse getStatus() {
		return status;
	}
	public void setStatus(StatusResponse status) {
		this.status = status;
	}
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}

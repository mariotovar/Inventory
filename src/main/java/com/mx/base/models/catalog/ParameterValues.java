package com.mx.base.models.catalog;

import java.util.HashMap;
import java.util.Map;

public class ParameterValues {
	
	private Map<String, String> values;
	
	public ParameterValues(){
		this.values = new HashMap<String, String>();
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "ParameterValues [values=" + values + "]";
	}
	
}

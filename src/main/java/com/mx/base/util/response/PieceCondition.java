package com.mx.base.util.response;

public enum PieceCondition {
	
	NE("NE", "NEW"),
	NEW_EX("NEW/EX", "NEW EXCHANGE"),
	OH_EX("OH/EX", "OVERHAUL"),
	OH_OUT("OH/OUT", "OVERHAUL OUTRIGTH"),
	SV_EX("SV/EX", "SERVICIABLE EXCHANGE"),
	SV_OUT("SV/OUT", "SERVICIABLE OUTRIGTH"),
	REP_EX("REP/EX", "REPAIR EXCHANGE"),
	REP_OUT("REP/OUT", "REPAIR OUTRIGTH"),
	AR("AR", "AS REMOVED"), 
	INSPECTED("INSPECTED", "INSPECCIONADO"),
	INSPECTED_EX("INSPECTED/EX", "INSPECCONADO EXCHANGE");
	
	PieceCondition(String key, String description) {
		this.key = key;
		this.description = description;
	}

	private String key;
	private String description;

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

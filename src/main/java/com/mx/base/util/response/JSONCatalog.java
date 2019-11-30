package com.mx.base.util.response;

import com.mx.base.abstractions.CatalogModel;

public class JSONCatalog {
	
	private CatalogModel catalog;
	private StatusResponse status;

	public JSONCatalog(){
		this.status = StatusResponse.SUCCESS;
	}	

	public StatusResponse getStatus() {
		return status;
	}
	public void setStatus(StatusResponse status) {
		this.status = status;
	}
	public CatalogModel getCatalog() {
		return catalog;
	}
	public void setCatalog(CatalogModel catalog) {
		this.catalog = catalog;
	}

}

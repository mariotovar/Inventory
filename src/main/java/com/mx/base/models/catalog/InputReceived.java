package com.mx.base.models.catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InputReceived {

	private Long lot;
	private int stock;
	private int receivedQty;
	private Date receiveDate;
	private String notes;
	private String serial;
	private String attach;
	public String getSerial() {
		return serial;
	}


	public void setSerial(String serial) {
		this.serial = serial;
	}

	private String user;
	private List<OutputSold> outputs;
	
	public InputReceived(){
		this.outputs = new ArrayList<OutputSold>();
	}
	

	public Long getLot() {
		return lot;
	}
	public void setLot(Long lot) {
		this.lot = lot;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getReceivedQty() {
		return receivedQty;
	}
	public void setReceivedQty(int receivedQty) {
		this.receivedQty = receivedQty;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public List<OutputSold> getOutputs() {
		return outputs;
	}
	public void setOutputs(List<OutputSold> outputs) {
		this.outputs = outputs;
	}
	
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}


	public String getLotNumber() {
		String lotNumber;		
		lotNumber = "L-" + String.format("%06d", this.getLot());
		return lotNumber;
	}			

	@Override
	public String toString() {
		return "InputReceived [lot=" + lot + ", receivedQty=" + receivedQty + ", receiveDate="
				+ receiveDate + ", notes=" + notes + ", user=" + user + ", outputs=" + outputs + "]";
	}
	
	
	
}

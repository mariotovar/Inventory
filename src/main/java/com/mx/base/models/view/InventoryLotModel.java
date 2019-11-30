package com.mx.base.models.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.mx.base.abstractions.ViewModel;

@MappedSuperclass
public class InventoryLotModel extends ViewModel{
	
	@Id
	@Column(name="_PK")	
	private Long lot;
	@Column(name="PK_PRODUCT")	
	private long pk;
	@Column(name="_VALUE")
	private String value;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="PIECE_CONDITION")
	private String condition;	
	@Column(name="BIN")
	private String bin;	
	@Column(name="COST_USD")
	private double costUSD;
	@Column(name="COST_MXN")
	private double costMXN;
	@Column(name="LIST_USD")
	private double listUSD;	
	@Column(name="LIST_MXN")
	private double listMXN;		
	@Column(name="SUPPLIER")
	private String supplier;	
	@Column(name="EXPIRED_DATE")
	private Date expiredDate;;	
	private int inputs;
	private int outputs;
	@Column(name="_CURRENT")
	private String current;	
	
	public Long getLot() {
		return lot;
	}
	public void setLot(Long lot) {
		this.lot = lot;
	}
	public long getPk() {
		return pk;
	}
	public void setPk(long pk) {
		this.pk = pk;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}		
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getBin() {
		return bin;
	}
	public void setBin(String bin) {
		this.bin = bin;
	}
	public double getCostUSD() {
		return costUSD;
	}
	public void setCostUSD(double costUSD) {
		this.costUSD = costUSD;
	}
	public double getCostMXN() {
		return costMXN;
	}
	public void setCostMXN(double costMXN) {
		this.costMXN = costMXN;
	}
	public double getListUSD() {
		return listUSD;
	}
	public void setListUSD(double listUSD) {
		this.listUSD = listUSD;
	}
	public double getListMXN() {
		return listMXN;
	}
	public void setListMXN(double listMXN) {
		this.listMXN = listMXN;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public int getInputs() {
		return inputs;
	}
	public void setInputs(int inputs) {
		this.inputs = inputs;
	}
	public int getOutputs() {
		return outputs;
	}
	public void setOutputs(int outputs) {
		this.outputs = outputs;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}	
	
	public String getLotNumber() {
		String lotNumber;		
		lotNumber = "L-" + String.format("%06d", this.getLot());
		return lotNumber;
	}			
	public int getStock() {
		int stock = 0;
		stock = (this.getInputs() - this.getOutputs());
		return stock;
	}	

}

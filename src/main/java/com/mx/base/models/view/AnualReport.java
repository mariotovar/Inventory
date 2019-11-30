package com.mx.base.models.view;

public class AnualReport implements Comparable<AnualReport>{
	
	public int year;
	public int month;
	public String type;
	public double amountMXN;
	public double amountUSD;
	public String user;
    
    public AnualReport(int year, int month, String type, double amountMXN, double amountUSD, String user) {
    	this.year = year;
    	this.month = month;
    	this.type = type;
    	this.amountMXN = amountMXN;
    	this.amountUSD = amountUSD;
    	this.user = user;
    }
    
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmountMXN() {
		return amountMXN;
	}
	public void setAmountMXN(double amountMXN) {
		this.amountMXN = amountMXN;
	}
	public double getAmountUSD() {
		return amountUSD;
	}
	public void setAmountUSD(double amountUSD) {
		this.amountUSD = amountUSD;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}


	@Override
	public int compareTo(AnualReport o) {
		 if (this.year == o.getYear() && this.getMonth()== o.getMonth() && this.getType().equals(o.getType())) {
             return 0;
         } else if (this.year < o.getYear()) {
             return -1;
         }
         return 1;
	}

}

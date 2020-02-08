package com.mx.base.util.functions;

import java.util.Calendar;

public class DateUtils {

	public static int getCurrentYear(){
		
		int year = 0;		
		year = Calendar.getInstance().get(Calendar.YEAR);	
		System.out.println("year: " + year);
		return year;
	}
	
}

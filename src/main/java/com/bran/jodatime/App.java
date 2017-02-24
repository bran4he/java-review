package com.bran.jodatime;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class App {

	public static void main(String[] args) {
		
		LocalDate today = LocalDate.now();
		System.out.println(today);	//2017-01-24

		LocalDateTime todayTime = LocalDateTime.now();
		System.out.println(todayTime);	//2017-01-24T20:43:44.207
		System.out.println(todayTime.plusHours(60));	//2017-01-27T08:44:19.049

		
		Date date = new Date();
		System.out.println(
				DateFormat
						.getDateInstance(DateFormat.FULL, Locale.CHINESE)
				        .format(date)
		        );
		//2017年1月24日 星期二
		System.out.println(
				DateFormat
						.getDateInstance(DateFormat.FULL, Locale.CHINESE)
				        .format(date)
		        );
		
		
	}

}

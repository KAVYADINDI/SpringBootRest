package com.salarydate;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class SalaryDate {

//	public static Object salaryDate(int month, int year) {
//
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//		LocalDate date = LocalDate.of(year, month, 1);
//		LocalDate endDate = date.with(lastDayOfMonth());
//		int day = endDate.getDayOfWeek().getValue();
//		if (day > 0 && day <= 7)
//			if (day == 7)
//				return endDate.minusDays(2).format(formatter);
//			else if (day == 6)
//				return endDate.minusDays(1).format(formatter);
//			else 
//				endDate=checkIfPublicHoliday(endDate);
//		return endDate.format(formatter);
//	}


//	private static LocalDate checkIfPublicHoliday(LocalDate date)
//	{
//        ArrayList<LocalDate> publicHolidays = new ArrayList<>();
//		publicHolidays.add(LocalDate.of(2018, 12, 31));
//
//	for (int i = 0; i < publicHolidays.size(); i++)
//	{
//		if(date.equals(publicHolidays))
//		{
//			date= date.minusDays(1);
//			int day = date.getDayOfWeek().getValue();
//			if (day > 0 && day <= 7)
//				if (day == 7)
//					return date.minusDays(2);
//				else if (day == 6)
//					return date.minusDays(1);
//		}	
//	}
//	return date;
//	}
	

	public static String salaryDate(LocalDate date) {

		LocalDate salaryDate = date;
		YearMonth month = YearMonth.from(date);
		LocalDate endDate = month.atEndOfMonth();
		int day = endDate.getDayOfWeek().getValue();
		if (day > 0 && day <= 7)
			if (day == 7)
				salaryDate = endDate.minusDays(2);
			else if (day == 6)
				salaryDate = endDate.minusDays(1);
			else
				salaryDate = endDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return salaryDate.format(formatter);
	}

}

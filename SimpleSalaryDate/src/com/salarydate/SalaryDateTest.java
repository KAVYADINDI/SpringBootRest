package com.salarydate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class SalaryDateTest {

	/*@Test
	void endDateSundaytest() {
		assertEquals("2018-09-28",SalaryDate.salaryDate(9,2018));
	}
	
	@Test
	void endDateSaturdaytest() {
		assertEquals("2018-06-29",SalaryDate.salaryDate(6,2018));
	}
	
	@Test
	void endDatePublicHolidaytest() {
		assertEquals("2018-12-28",SalaryDate.salaryDate(12,2018));
	}*/
	
	@Test
	void SalaryDatetest() {
		LocalDate date=LocalDate.now();
		assertEquals("2018-10-31",SalaryDate.salaryDate(date));
		
//		assertEquals("2018-09-28",SalaryDate.salaryDate(LocalDate.of(2018,9,5)));
//		assertEquals("2018-11-30",SalaryDate.salaryDate(LocalDate.of(2018,11,16)));
	}
}

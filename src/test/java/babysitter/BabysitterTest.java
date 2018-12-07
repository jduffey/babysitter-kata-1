package babysitter;


import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

public class BabysitterTest {
	
	
	Babysitter sophie = new Babysitter();
	
//	@Test
//	public void shouldReturnTotalHoursWorkedGivenStartAndEndTimes() {
//		
//		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
//		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 20, 0);
//		long elapsedTime = sophie.compute(start, end);
//		assertEquals(3, elapsedTime);
//		
//	}
//	
//	@Test
//	public void shouldCalculateTotalHoursWorkedGivenStartAndEndTimes() {
//		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
//		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 21, 0);
//		long elapsedTime = sophie.compute(start, end);
//		assertEquals(4, elapsedTime);
//	
//	}
//	
//	@Test
//	public void shouldCalculateTotalHoursWorkedGivenStartTimeAndEndTimePastMidnight() {
//		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
//		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 1, 0);
//		long elapsedTime = sophie.compute(start, end);
//		assertEquals(8, elapsedTime);
//	}
	
	@Test
	public void shouldCalculateWagesForFamilyAGivenStartAndEndTimeBefore11() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 20, 0);
		int pay = sophie.compute("A", start, end);
		assertEquals(45, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyAGivenStartBefore11AndEndTimeAfter11() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 23, 30);
		int pay = sophie.compute("A", start, end);
		assertEquals(90, pay);		
	}
	
}

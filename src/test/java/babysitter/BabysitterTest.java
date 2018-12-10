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
	public void shouldCalculateWagesForFamilyAGivenStartAndEndTimeBefore23() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 20, 0);
		int pay = sophie.compute("A", start, end);
		assertEquals(45, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyAGivenStartBefore23AndEndTimeAfter23ButBeforeMidnight() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 23, 0);
		int pay = sophie.compute("A", start, end);
		assertEquals(90, pay);		
	}
	
	@Test
	public void shouldCalculateWagesForFamilyAgivenStartBefore23AndEndAfterMidnight() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 02, 0);
		int pay = sophie.compute("A", start, end);
		assertEquals(150, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyAGivenStartAfter23AndEndAfterMidnight() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 23, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 02, 0);
		int pay = sophie.compute("A", start, end);
		assertEquals(60, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyCGivenAStartAndEndTimeBefore2100() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 19, 0);
		int pay = sophie.compute("C", start, end);
		assertEquals(42, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyCGivenStartAndEndAfter2100() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 21, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 23, 0);
		int pay = sophie.compute("C", start, end);
		assertEquals(30, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyCGivenStartBefore2100AndEndAfter2100ButBeforeMidnight() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 18, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 23, 0);
		int pay = sophie.compute("C", start, end);
		assertEquals(93, pay);
	}
	@Test
	public void shouldCalculateWagesForFamilyCGivenStartBefore2100AndEndAfterMidnight() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 18, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 2, 0);
		int pay = sophie.compute("C", start, end);
		assertEquals(63+75, pay);
	}
	@Test
	public void shouldCalculateWagesForFamilyCGivenStartTimeAfterMidnight() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 0, 30 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 2, 30);
		int pay = sophie.compute("C", start, end);
		assertEquals(30, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyBGivenStartAndEndBefore2200() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 17, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 20, 0);
		int pay = sophie.compute("B", start, end);
		assertEquals(36, pay);
	}
	

	
}

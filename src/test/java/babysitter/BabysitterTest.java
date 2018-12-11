package babysitter;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
	public void shouldCalculateWagesForFamilyAGivenStartAndEndTimeBefore23() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 20, 0);
		int pay = sophie.compute("A", start, end);
		assertEquals(45, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyAGivenStartBefore23AndEndTimeAfter23ButBeforeMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 23, 0);
		int pay = sophie.compute("A", start, end);
		assertEquals(90, pay);		
	}
	
	@Test
	public void shouldCalculateWagesForFamilyAgivenStartBefore23AndEndAfterMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 02, 0);
		int pay = sophie.compute("A", start, end);
		assertEquals(150, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyAGivenStartAfter23AndEndAfterMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 23, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 02, 0);
		int pay = sophie.compute("A", start, end);
		assertEquals(60, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyCGivenAStartAndEndTimeBefore2100() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 19, 0);
		int pay = sophie.compute("C", start, end);
		assertEquals(42, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyCGivenStartAndEndAfter2100() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 21, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 23, 0);
		int pay = sophie.compute("C", start, end);
		assertEquals(30, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyCGivenStartBefore2100AndEndAfter2100ButBeforeMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 18, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 23, 0);
		int pay = sophie.compute("C", start, end);
		assertEquals(93, pay);
	}
	@Test
	public void shouldCalculateWagesForFamilyCGivenStartBefore2100AndEndAfterMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 18, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 2, 0);
		int pay = sophie.compute("C", start, end);
		assertEquals(63+75, pay);
	}
	@Test
	public void shouldCalculateWagesForFamilyCGivenStartTimeAfterMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 0, 30 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 2, 30);
		int pay = sophie.compute("C", start, end);
		assertEquals(30, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyBGivenStartAndEndBefore2200() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 17, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 20, 0);
		int pay = sophie.compute("B", start, end);
		assertEquals(36, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyBGivenStartAndEndAfterMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 0, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 2, 0);
		int pay = sophie.compute("B", start, end);
		assertEquals(32, pay);
	}

	@Test
	public void shouldCalculateWagesForFamilyBGivenStartAfter2200AndEndBeforeMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 22, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 23, 0);
		int pay = sophie.compute("B", start, end);
		assertEquals(8, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyBGivenStartAfter2200AndEndAtMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 22, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 14, 0, 0);
		int pay = sophie.compute("B", start, end);
		assertEquals(16, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyBGivenStartBefore2200AndEndBeforeMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 18, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 23, 0);
		int pay = sophie.compute("B", start, end);
		assertEquals(56, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyBGivenStartBefore2200AndEndAtMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 18, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 14, 0, 0);
		int pay = sophie.compute("B", start, end);
		assertEquals(64, pay);
	}

	@Test
	public void shouldCalculateWagesForFamilyBGivenStartBefore2200AndEndAfterMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 18, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 14, 2, 0);
		int pay = sophie.compute("B", start, end);
		assertEquals(12 * 4 + 8 * 2 + 16 * 2, pay);
	}
	
	@Test
	public void shouldCalculateWagesForFamilyBGivenStartAfter2200AndEndAfterMidnight() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 22, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 14, 2, 0);
		int pay = sophie.compute("B", start, end);
		assertEquals( 8 * 2 + 16 * 2, pay);
	}
	
	@Test
	public void shouldFailForEndBeforeStart() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 22, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 2, 0);
		int pay = sophie.compute("B", start, end);
		assertEquals(0, pay);
	}
	
	@Test
	public void shouldFailForStartBeforeEarliest() throws InvalidTimesException {
		LocalDateTime start = LocalDateTime.of(2018, 12, 13, 15, 0 );
		LocalDateTime end = LocalDateTime.of(2018, 12, 13, 23, 0);
		int pay = sophie.compute("B", start, end);
		assertEquals(0, pay);
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

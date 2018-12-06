package babysitter;


import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

public class BabysitterTest {
	
	
	Babysitter sophie = new Babysitter();
	
	@Test
	public void shouldReturnTotalHoursWorkedGivenStartAndEndTimes() {
		
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 20, 0);
		int elapsedTime = sophie.compute(start, end);
		assertEquals(elapsedTime, 3);
		
	}
	
	@Test
	public void shouldCalculateTotalHoursWorkedGivenStartAndEndTimes() {
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 17, 0);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 21, 0);
		int elapsedTime = sophie.compute(start, end);
		assertEquals(elapsedTime, 4);
	
	}
	
}

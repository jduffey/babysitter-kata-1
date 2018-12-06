package babysitter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Babysitter {

	public long compute(LocalDateTime start, LocalDateTime end) {
		
		LocalTime endTime = end.toLocalTime();
		LocalTime startTime = start.toLocalTime();
		
		long elapsedHours = Duration.between(startTime, endTime).toHours(); 
		if ((start.getDayOfMonth() - end.getDayOfMonth()) == -1) {
			elapsedHours += 24;
		}
		return elapsedHours;
	}

}

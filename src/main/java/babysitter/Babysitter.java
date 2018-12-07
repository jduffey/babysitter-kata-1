package babysitter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Babysitter {

	public int compute(String family, LocalDateTime start, LocalDateTime end) {
		LocalTime endTime = end.toLocalTime();
		LocalTime startTime = start.toLocalTime();
		int pay = 0;
		if (family.equalsIgnoreCase("A")) {
			long elapsedHours = Duration.between(startTime, endTime).toHours();
			if ((start.getDayOfMonth() - end.getDayOfMonth()) == -1) {
			elapsedHours += 24;
		}
			pay = (int) (elapsedHours * 15);
		}
		return pay;
	}

}

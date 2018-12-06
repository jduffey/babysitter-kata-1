package babysitter;

import java.time.LocalDateTime;

public class Babysitter {

	public int compute(LocalDateTime start, LocalDateTime end) {
		int elapsedHours = end.getHour() - start.getHour();
		return elapsedHours;
	}

}

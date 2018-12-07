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
			LocalTime rateChange = LocalTime.parse("23:00");
			if (startTime.isBefore(rateChange) && endTime.isBefore(rateChange)) {
				long elapsedMinutes = Duration.between(start, end).toMinutes();
				pay = (int) (elapsedMinutes/60 * 15);
			}
			long secondElapsedMinutes = 0;

			if(startTime.isBefore(rateChange) && endTime.isAfter(rateChange)) {
				long firstElapsedMinutes = Duration.between(startTime, rateChange).toMinutes();
				System.out.println(firstElapsedMinutes);
				;
				if (endTime.isBefore(LocalTime.NOON)) { //in other words, after midnight
					secondElapsedMinutes = Duration.between(rateChange, LocalTime.MAX).toMinutes();
					secondElapsedMinutes += Duration.between(LocalTime.MIDNIGHT, endTime).toMinutes();
				}
				if (endTime.isBefore(LocalTime.MAX)) { //before midnight
					secondElapsedMinutes = Duration.between(rateChange, endTime).toMinutes();
				}
			


				pay = (int) (firstElapsedMinutes / 60 * 15 + secondElapsedMinutes / 60 * 20 );
			}
		}
		return pay;
	}

}

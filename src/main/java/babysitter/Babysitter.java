package babysitter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Babysitter {

	public int compute(String family, LocalDateTime start, LocalDateTime end) {
		LocalTime endTime = end.toLocalTime();
		LocalTime startTime = start.toLocalTime();
		LocalTime earliestStart = LocalTime.parse("17:00");
		LocalTime latestEnd = LocalTime.parse("04:01");

		int pay = 0;

		if (family.equalsIgnoreCase("A")) {
			LocalTime rateChange = LocalTime.parse("23:00");
			

			if (startTime.isBefore(rateChange) && endTime.isBefore(rateChange) && endTime.isAfter(earliestStart)) { 
				long elapsedMinutes = Duration.between(start, end).toMinutes();
				pay = (int) (elapsedMinutes / 60 * 15);
			} else if (startTime.isBefore(rateChange) && (endTime.isAfter(rateChange) || endTime.isBefore(latestEnd))) {
				long firstElapsedMinutes = Duration.between(startTime, rateChange).toMinutes();
				System.out.println(firstElapsedMinutes);
				long secondElapsedMinutes = 0;

				if (endTime.isBefore(latestEnd)) { // ends after midnight
					secondElapsedMinutes = Duration.between(rateChange, LocalTime.MAX).toMinutes();
					System.out.println("before midnight" + secondElapsedMinutes);
					secondElapsedMinutes += Duration.between(LocalTime.MIDNIGHT, endTime).toMinutes();
					System.out.println("aftermidnight" + secondElapsedMinutes);
				} else if (endTime.isBefore(LocalTime.MAX)) { // ends before midnight, after 11PM
					secondElapsedMinutes = Duration.between(rateChange, endTime).toMinutes();
				}

				pay = (int) ((firstElapsedMinutes / 60 * 15) + (secondElapsedMinutes / 60 * 20));
			}
		}
		return pay;
	}

}

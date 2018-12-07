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

			} else if (startTime.isAfter(rateChange)) {
				long elapsedMinutes = Duration.between(start, end).toMinutes();
				pay = (int) (elapsedMinutes / 60 * 20);

			} else if (startTime.isBefore(rateChange) && (endTime.isAfter(rateChange) || endTime.isBefore(latestEnd))) {
				long firstElapsedMinutes = Duration.between(startTime, rateChange).toMinutes();
				long secondElapsedMinutes = 0;

				if (endTime.isBefore(latestEnd)) { // ends after midnight
					secondElapsedMinutes = Duration.between(rateChange, LocalTime.MAX).toMinutes();
					secondElapsedMinutes += Duration.between(LocalTime.MIDNIGHT, endTime).toMinutes();
				} else if (endTime.isBefore(LocalTime.MAX)) { // ends before midnight, after 11PM
					secondElapsedMinutes = Duration.between(rateChange, endTime).toMinutes();
				}
				pay = (int) ((firstElapsedMinutes / 60 * 15) + (secondElapsedMinutes / 60 * 20));

			}

		} else if (family.equalsIgnoreCase("C")) {
			LocalTime rateChange = LocalTime.parse("21:00");
			long firstElapsedMinutes = 0;
			long secondElapsedMinutes = 0;
			
			if( endTime.isBefore(rateChange) && endTime.isAfter(earliestStart)){
				firstElapsedMinutes = Duration.between(start, end).toMinutes();
			}
			else if (startTime.isAfter(rateChange) || startTime.isBefore(latestEnd) ) {
				secondElapsedMinutes = Duration.between(start, end).toMinutes();
			}
			
			pay = (int) ((firstElapsedMinutes / 60 * 21) + (secondElapsedMinutes/60 * 15));
	
		}
		return pay;
	}

}

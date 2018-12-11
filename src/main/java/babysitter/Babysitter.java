package babysitter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Babysitter {

	public int compute(String family, LocalDateTime start, LocalDateTime end) {
		LocalTime endTime = end.toLocalTime();
		LocalTime startTime = start.toLocalTime(); // maybe start times always get rounded down and end times always get
													// rounded up?
		LocalTime earliestStart = LocalTime.parse("17:00");
		LocalTime latestEnd = LocalTime.parse("04:01");

		int pay = 0;

		if (family.equalsIgnoreCase("A")) {
			LocalTime rateChange = LocalTime.parse("23:00");
			long firstElapsedMinutes = 0;
			long secondElapsedMinutes = 0;

			if ((endTime.isBefore(rateChange) || endTime.equals(rateChange)) && endTime.isAfter(earliestStart)) {
				firstElapsedMinutes = Duration.between(start, end).toMinutes() + 1;
			} else if (startTime.isAfter(rateChange) || startTime.equals(rateChange) || startTime.isBefore(latestEnd)) {
				secondElapsedMinutes = Duration.between(start, end).toMinutes() + 1;
			} else if (startTime.isBefore(rateChange) && (endTime.isAfter(rateChange) || endTime.isBefore(latestEnd))) {
				firstElapsedMinutes = Duration.between(startTime, rateChange).toMinutes() + 1;
				if (endTime.isBefore(latestEnd)) { // ends after midnight
					secondElapsedMinutes = Duration.between(rateChange, LocalTime.MAX).toMinutes() + 1;
					secondElapsedMinutes += Duration.between(LocalTime.MIDNIGHT, endTime).toMinutes() + 1;
				} else if (endTime.isBefore(LocalTime.MAX)) {
					secondElapsedMinutes = Duration.between(rateChange, endTime).toMinutes() + 1; 
				}
			}
			pay = (int) ((firstElapsedMinutes / 60 * 15) + (secondElapsedMinutes / 60 * 20));

		} else if (family.equalsIgnoreCase("C")) {
			LocalTime rateChange = LocalTime.parse("21:00");
			long firstElapsedMinutes = 0;
			long secondElapsedMinutes = 0;
			if ((endTime.isBefore(rateChange) || endTime.equals(rateChange)) && endTime.isAfter(earliestStart)) {
				firstElapsedMinutes = Duration.between(start, end).toMinutes() + 1;
			} else if (startTime.isAfter(rateChange) || startTime.equals(rateChange) || startTime.isBefore(latestEnd)) {
				secondElapsedMinutes = Duration.between(start, end).toMinutes() + 1;
			} else if (startTime.isBefore(rateChange) && (endTime.isAfter(rateChange) || endTime.isBefore(latestEnd))) {
				firstElapsedMinutes = Duration.between(startTime, rateChange).toMinutes() + 1;
				if (endTime.isBefore(latestEnd)) { // ends after midnight
					secondElapsedMinutes = Duration.between(rateChange, LocalTime.MAX).toMinutes() + 1;
					secondElapsedMinutes += Duration.between(LocalTime.MIDNIGHT, endTime).toMinutes() + 1;
				} else if (endTime.isBefore(LocalTime.MAX)) {
					secondElapsedMinutes = Duration.between(rateChange, endTime).toMinutes() + 1;
				}
			}
			pay = (int) ((firstElapsedMinutes / 60 * 21) + (secondElapsedMinutes / 60 * 15));
		} else if(family.equalsIgnoreCase("B")) {
			LocalTime rateChange1 = LocalTime.parse("22:00");
			LocalTime rateChange2 = LocalTime.MIDNIGHT;
			long firstElapsedMinutes = 0;
			long thirdElapsedMinutes = 0;
			
			if ((endTime.isBefore(rateChange1) || endTime.equals(rateChange1)) && endTime.isAfter(earliestStart)) {
				firstElapsedMinutes = Duration.between(start, end).toMinutes() + 1;
			} else if (startTime.isBefore(latestEnd)) {
				thirdElapsedMinutes = Duration.between(start, end).toMinutes() + 1;
			}
			pay = (int) (firstElapsedMinutes / 60 * 12 + thirdElapsedMinutes / 60 * 16);
		}
		
		
		
		
		
		
		return pay;
	}

}

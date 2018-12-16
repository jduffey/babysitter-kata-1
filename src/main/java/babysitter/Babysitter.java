package babysitter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Babysitter {

	private static final String EARLIEST_ALLOWED_START_TIME = "17:00";
	private static final String LATEST_ALLOWED_END_TIME = "04:01";

	public int compute(String family, LocalDateTime startShiftTime, LocalDateTime endShiftTime) throws InvalidTimesException {

		LocalTime startTime = startShiftTime.toLocalTime();
		LocalTime endTime = endShiftTime.toLocalTime();

		LocalTime earliestStart = LocalTime.parse(EARLIEST_ALLOWED_START_TIME);
		LocalTime latestEnd = LocalTime.parse(LATEST_ALLOWED_END_TIME);

		int pay = 0;

		if (endShiftTime.isBefore(startShiftTime) || (endTime.isAfter(latestEnd) && endTime.isBefore(earliestStart))
				|| (startTime.isBefore(earliestStart) && startTime.isAfter(latestEnd))
				|| (Duration.between(startShiftTime, endShiftTime).toHours() > 11)) {
			throw new InvalidTimesException();
		}

		if (family.equalsIgnoreCase("A") || family.equalsIgnoreCase("C")) {
			// Family A pays $15 per hour before 11pm, and $20 per hour the rest of the night
			// Family C pays $21 per hour before 9pm, then $15 the rest of the night
			long firstElapsedMinutes = 0;
			long secondElapsedMinutes = 0;
			LocalTime rateChange = null;

			if (family.equalsIgnoreCase("A")) {
				rateChange = LocalTime.parse("23:00");
			} else if (family.equalsIgnoreCase("C")) {
				rateChange = LocalTime.parse("21:00");
			}

			if ((endTime.isBefore(rateChange) || endTime.equals(rateChange)) && endTime.isAfter(earliestStart)) {
				firstElapsedMinutes = Duration.between(startShiftTime, endShiftTime).toMinutes() + 1;
			} else if (startTime.isAfter(rateChange) || startTime.equals(rateChange) || startTime.isBefore(latestEnd)) {
				secondElapsedMinutes = Duration.between(startShiftTime, endShiftTime).toMinutes() + 1;
			} else if (startTime.isBefore(rateChange) && (endTime.isAfter(rateChange) || endTime.isBefore(latestEnd))) {
				firstElapsedMinutes = Duration.between(startTime, rateChange).toMinutes() + 1;
				if (endTime.isBefore(latestEnd)) {
					secondElapsedMinutes = Duration.between(rateChange, LocalTime.MAX).toMinutes() + 1;
					secondElapsedMinutes += Duration.between(LocalTime.MIDNIGHT, endTime).toMinutes() + 1;
				} else if (endTime.isBefore(LocalTime.MAX)) {
					secondElapsedMinutes = Duration.between(rateChange, endTime).toMinutes() + 1;
				}
			}
			if (family.equalsIgnoreCase("A")) {
				pay = (int) ((firstElapsedMinutes / 60 * 15) + (secondElapsedMinutes / 60 * 20));
			} else if (family.equalsIgnoreCase("C")) {
				pay = (int) ((firstElapsedMinutes / 60 * 21) + (secondElapsedMinutes / 60 * 15));

			}

		} else if (family.equalsIgnoreCase("B")) {
			// Family B pays $12 per hour before 10pm, $8 between 10 and 12, and $16 the
			// rest of the night
			LocalTime rateChange1 = LocalTime.parse("22:00");
			LocalTime rateChange2 = LocalTime.MIDNIGHT;
			long firstElapsedMinutes = 0;
			long thirdElapsedMinutes = 0;
			long secondElapsedMinutes = 0;

			if ((endTime.isBefore(rateChange1) || endTime.equals(rateChange1)) && endTime.isAfter(earliestStart)) {
				firstElapsedMinutes = Duration.between(startShiftTime, endShiftTime).toMinutes() + 1;

			} else if (startTime.isBefore(latestEnd)) {
				thirdElapsedMinutes = Duration.between(startShiftTime, endShiftTime).toMinutes() + 1;

			} else if (startTime.isAfter(rateChange1) || startTime.equals(rateChange1)) {
				if (endTime.isBefore(LocalTime.MAX) && endTime.isAfter(rateChange1)) {
					secondElapsedMinutes = Duration.between(startShiftTime, endShiftTime).toMinutes() + 1;
				} else if (endTime.equals(rateChange2)) {
					secondElapsedMinutes = Duration.between(startTime, LocalTime.MAX).toMinutes() + 1;
				} else if (endTime.isBefore(latestEnd)) {
					secondElapsedMinutes = Duration.between(startTime, LocalTime.MAX).toMinutes() + 1;
					thirdElapsedMinutes = Duration.between(LocalTime.MIDNIGHT, endTime).toMinutes() + 1;
				}

			} else if (startTime.isBefore(rateChange1)) {

				if ((endTime.isAfter(rateChange1))) {
					firstElapsedMinutes = Duration.between(startTime, rateChange1).toMinutes() + 1;
					secondElapsedMinutes = Duration.between(rateChange1, endTime).toMinutes() + 1;
				} else if (endTime.equals(rateChange2)) {
					firstElapsedMinutes = Duration.between(startTime, rateChange1).toMinutes() + 1;
					secondElapsedMinutes = Duration.between(rateChange1, LocalTime.MAX).toMinutes() + 1;
				} else if (endTime.isBefore(latestEnd) || endTime.equals(latestEnd)) {
					firstElapsedMinutes = Duration.between(startTime, rateChange1).toMinutes() + 1;
					secondElapsedMinutes = Duration.between(rateChange1, LocalTime.MAX).toMinutes() + 1;
					thirdElapsedMinutes += Duration.between(LocalTime.MIDNIGHT, endTime).toMinutes() + 1;
				} else if (endTime.isBefore(LocalTime.MAX)) {
					firstElapsedMinutes = Duration.between(startTime, rateChange1).toMinutes() + 1;
					secondElapsedMinutes = Duration.between(rateChange1, endTime).toMinutes() + 1;
				}

			}

			pay = (int) ((firstElapsedMinutes / 60 * 12) + (thirdElapsedMinutes / 60 * 16)
					+ (secondElapsedMinutes / 60 * 8));
		}

		return pay;
	}

}

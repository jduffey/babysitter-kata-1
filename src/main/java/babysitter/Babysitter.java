package babysitter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Babysitter {

	private static final String EARLIEST_ALLOWED_START_TIME = "17:00";
	private static final String LATEST_ALLOWED_END_TIME = "04:01";
	private static final LocalTime FAMILY_B_RATE_CHANGE_TIME_ONE = LocalTime.parse("22:00");
	private static final LocalTime FAMILY_B_RATE_CHANGE_TIME_TWO = LocalTime.MIDNIGHT;

	public int compute(String family, LocalDateTime startShiftTime, LocalDateTime endShiftTime) throws InvalidTimesException {

		LocalTime startTime = startShiftTime.toLocalTime();
		LocalTime endTime = endShiftTime.toLocalTime();

		LocalTime earliestAllowedStart = LocalTime.parse(EARLIEST_ALLOWED_START_TIME);
		LocalTime latestAllowedEnd = LocalTime.parse(LATEST_ALLOWED_END_TIME);

		int pay = 0;

		validateShiftTimes(startShiftTime, endShiftTime, startTime, endTime, earliestAllowedStart, latestAllowedEnd);

		if (family.equalsIgnoreCase("A") || family.equalsIgnoreCase("C")) {
			// Family A pays $15 per hour before 11pm, and $20 per hour the rest of the night
			// Family C pays $21 per hour before 9pm, then $15 the rest of the night
			long firstElapsedMinutes = 0;
			long secondElapsedMinutes = 0;
			LocalTime rateChangeTime = null;

			if (family.equalsIgnoreCase("A")) {
				rateChangeTime = LocalTime.parse("23:00");
			} else if (family.equalsIgnoreCase("C")) {
				rateChangeTime = LocalTime.parse("21:00");
			}

			if ((endTime.isBefore(rateChangeTime) || endTime.equals(rateChangeTime)) && endTime.isAfter(earliestAllowedStart)) {
				firstElapsedMinutes = Duration.between(startShiftTime, endShiftTime).toMinutes() + 1;
			} else if (startTime.isAfter(rateChangeTime) || startTime.equals(rateChangeTime) || startTime.isBefore(latestAllowedEnd)) {
				secondElapsedMinutes = Duration.between(startShiftTime, endShiftTime).toMinutes() + 1;
			} else if (startTime.isBefore(rateChangeTime) && (endTime.isAfter(rateChangeTime) || endTime.isBefore(latestAllowedEnd))) {
				firstElapsedMinutes = Duration.between(startTime, rateChangeTime).toMinutes() + 1;
				if (endTime.isBefore(latestAllowedEnd)) {
					secondElapsedMinutes = Duration.between(rateChangeTime, LocalTime.MAX).toMinutes() + 1;
					secondElapsedMinutes += Duration.between(LocalTime.MIDNIGHT, endTime).toMinutes() + 1;
				} else if (endTime.isBefore(LocalTime.MAX)) {
					secondElapsedMinutes = Duration.between(rateChangeTime, endTime).toMinutes() + 1;
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

			long workedMinutesEarlyShift = 0;
			long workedMinutesMiddleShift = 0;
			long workedMinutesLateShift = 0;

			if ((endTime.isBefore(FAMILY_B_RATE_CHANGE_TIME_ONE) || endTime.equals(FAMILY_B_RATE_CHANGE_TIME_ONE)) && endTime.isAfter(earliestAllowedStart)) {
				workedMinutesEarlyShift = Duration.between(startShiftTime, endShiftTime).toMinutes() + 1;

			} else if (startTime.isBefore(latestAllowedEnd)) {
				workedMinutesLateShift = Duration.between(startShiftTime, endShiftTime).toMinutes() + 1;

			} else if (startTime.isAfter(FAMILY_B_RATE_CHANGE_TIME_ONE) || startTime.equals(FAMILY_B_RATE_CHANGE_TIME_ONE)) {
				if (endTime.isBefore(LocalTime.MAX) && endTime.isAfter(FAMILY_B_RATE_CHANGE_TIME_ONE)) {
					workedMinutesMiddleShift = Duration.between(startShiftTime, endShiftTime).toMinutes() + 1;
				} else if (endTime.equals(FAMILY_B_RATE_CHANGE_TIME_TWO)) {
					workedMinutesMiddleShift = Duration.between(startTime, LocalTime.MAX).toMinutes() + 1;
				} else if (endTime.isBefore(latestAllowedEnd)) {
					workedMinutesMiddleShift = Duration.between(startTime, LocalTime.MAX).toMinutes() + 1;
					workedMinutesLateShift = Duration.between(LocalTime.MIDNIGHT, endTime).toMinutes() + 1;
				}

			} else if (startTime.isBefore(FAMILY_B_RATE_CHANGE_TIME_ONE)) {

				if ((endTime.isAfter(FAMILY_B_RATE_CHANGE_TIME_ONE))) {
					workedMinutesEarlyShift = Duration.between(startTime, FAMILY_B_RATE_CHANGE_TIME_ONE).toMinutes() + 1;
					workedMinutesMiddleShift = Duration.between(FAMILY_B_RATE_CHANGE_TIME_ONE, endTime).toMinutes() + 1;
				} else if (endTime.equals(FAMILY_B_RATE_CHANGE_TIME_TWO)) {
					workedMinutesEarlyShift = Duration.between(startTime, FAMILY_B_RATE_CHANGE_TIME_ONE).toMinutes() + 1;
					workedMinutesMiddleShift = Duration.between(FAMILY_B_RATE_CHANGE_TIME_ONE, LocalTime.MAX).toMinutes() + 1;
				} else if (endTime.isBefore(latestAllowedEnd) || endTime.equals(latestAllowedEnd)) {
					workedMinutesEarlyShift = Duration.between(startTime, FAMILY_B_RATE_CHANGE_TIME_ONE).toMinutes() + 1;
					workedMinutesMiddleShift = Duration.between(FAMILY_B_RATE_CHANGE_TIME_ONE, LocalTime.MAX).toMinutes() + 1;
					workedMinutesLateShift += Duration.between(LocalTime.MIDNIGHT, endTime).toMinutes() + 1;
				} else if (endTime.isBefore(LocalTime.MAX)) {
					workedMinutesEarlyShift = Duration.between(startTime, FAMILY_B_RATE_CHANGE_TIME_ONE).toMinutes() + 1;
					workedMinutesMiddleShift = Duration.between(FAMILY_B_RATE_CHANGE_TIME_ONE, endTime).toMinutes() + 1;
				}

			}

			pay = (int) ((workedMinutesEarlyShift / 60 * 12) + (workedMinutesLateShift / 60 * 16)
					+ (workedMinutesMiddleShift / 60 * 8));
		}

		return pay;
	}

	private void validateShiftTimes(LocalDateTime startShiftTime, LocalDateTime endShiftTime, LocalTime startTime, LocalTime endTime, LocalTime earliestAllowedStart, LocalTime latestAllowedEnd) throws InvalidTimesException {
		if (endShiftTime.isBefore(startShiftTime) || (endTime.isAfter(latestAllowedEnd) && endTime.isBefore(earliestAllowedStart))
				|| (startTime.isBefore(earliestAllowedStart) && startTime.isAfter(latestAllowedEnd))
				|| (Duration.between(startShiftTime, endShiftTime).toHours() > 11)) {
			throw new InvalidTimesException();
		}
	}

}

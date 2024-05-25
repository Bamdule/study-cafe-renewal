package io.spring.studycafe.domain.common;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeInfoCalculator {

    public static TimeInfo createElapsedTimeInfo(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            throw new IllegalArgumentException(String.format("parameter is invalid [startDateTime=%s, endDateTime=%s]", startDateTime, endDateTime));
        }

        Duration duration = Duration.between(startDateTime, endDateTime);
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        return new TimeInfo(days, hours, minutes);
    }

    public static TimeInfo createInMinutes(long totalMinutes) {
        long days = totalMinutes / (24 * 60);
        long hours = (totalMinutes % (24 * 60)) / 60;
        long minutes = totalMinutes % 60;
        return new TimeInfo(days, hours, minutes);
    }

    public static TimeInfo subtract(TimeInfo start, TimeInfo end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("start와 end는 필수 값입니다.");
        }

        long diff = start.getTotalMinutes() - end.getTotalMinutes();

        // 차이가 음수일 경우 처리
        if (diff < 0) {
            return new TimeInfo(0, 0, 0);
        }

        return TimeInfo.createInMinutes(diff);
    }

    public static TimeInfo add(TimeInfo time1, TimeInfo time2) {
        if (time1 == null || time2 == null) {
            throw new IllegalArgumentException("time1와 time2는 필수 값입니다.");
        }

        long totalMinutes = time1.getTotalMinutes() + time2.getTotalMinutes();

        return TimeInfo.createInMinutes(totalMinutes);
    }

    /**
     * 두 시간을 비교하는 메소드
     *
     * @param start
     * @param end
     * @return 0 = same, positive = start is larger, negative = end is larger
     */
    public static int compare(TimeInfo start, TimeInfo end) {
        long totalMinutesFirst = start.getTotalMinutes();
        long totalMinutesSecond = end.getTotalMinutes();

        return Long.compare(totalMinutesFirst, totalMinutesSecond);
    }
}

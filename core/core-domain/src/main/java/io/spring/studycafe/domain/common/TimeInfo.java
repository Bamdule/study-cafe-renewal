package io.spring.studycafe.domain.common;

import java.time.Duration;
import java.time.LocalDateTime;

public record TimeInfo(long days, long hours, long minutes) {
    public static TimeInfo createElapsedTimeInfo(LocalDateTime startDateTime, LocalDateTime endDateTime) {
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
            throw new IllegalArgumentException("두 번째 시간이 첫 번째 시간보다 더 큽니다.");
        }

        return TimeInfo.createInMinutes(diff);
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

    private long getTotalMinutes() {
        return (this.days * 24 * 60) + (this.hours * 60) + this.minutes;
    }

    @Override
    public String toString() {
        return String.format("%2d일 %d시간 %d분", days, hours, minutes);
    }
}

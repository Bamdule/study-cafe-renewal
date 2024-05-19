package io.spring.studycafe.domain.common;

public record TimeInfo(long days, long hours, long minutes) {

    public boolean isSmallerThan(TimeInfo time) {
        if (time == null) {
            throw new IllegalArgumentException("time must not be null");
        }

        return this.getTotalMinutes() < time.getTotalMinutes();
    }

    public boolean isBiggerThan(TimeInfo time) {
        if (time == null) {
            throw new IllegalArgumentException("time must not be null");
        }

        return this.getTotalMinutes() > time.getTotalMinutes();
    }

    public static TimeInfo createInMinutes(long totalMinutes) {
        long days = totalMinutes / (24 * 60);
        long hours = (totalMinutes % (24 * 60)) / 60;
        long minutes = totalMinutes % 60;
        return new TimeInfo(days, hours, minutes);
    }

    public long getTotalMinutes() {
        return (this.days * 24 * 60) + (this.hours * 60) + this.minutes;
    }

    public boolean isEmpty() {
        return this.getTotalMinutes() < 1;
    }

    @Override
    public String toString() {
        return String.format("%2d일 %d시간 %d분", days, hours, minutes);
    }
}

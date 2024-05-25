package io.spring.studycafe.scheduler;

import io.spring.studycafe.batch.SeatExpirationValidationBatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
public class SeatScheduler {


    private final SeatExpirationValidationBatch seatExpirationValidationBatch;

    public SeatScheduler(SeatExpirationValidationBatch seatExpirationValidationBatch) {
        this.seatExpirationValidationBatch = seatExpirationValidationBatch;
    }

    @Scheduled(fixedDelay = 60000)
    public void alive() {
        log.info("alive");
    }

    @Transactional
    @Scheduled(fixedDelay = 60000)
    public void seat() {
        seatExpirationValidationBatch.batch(LocalDateTime.now());
    }
}

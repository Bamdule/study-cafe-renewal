package io.spring.studycafe.batch;

import io.spring.studycafe.domain.studycafe.seat.SeatRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SeatExpirationValidationBatch {


    private final SeatExpirationValidationService seatExpirationValidationService;
    private final SeatRepository seatRepository;

    public SeatExpirationValidationBatch(SeatExpirationValidationService seatExpirationValidationService, SeatRepository seatRepository) {
        this.seatExpirationValidationService = seatExpirationValidationService;
        this.seatRepository = seatRepository;
    }

    public void batch(LocalDateTime now) {
        seatRepository.findAllInUse()
            .stream()
            .forEach(seatExpirationValidationService::verifyExpiration);
    }
}

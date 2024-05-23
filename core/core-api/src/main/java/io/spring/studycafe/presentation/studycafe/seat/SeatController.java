package io.spring.studycafe.presentation.studycafe.seat;

import io.spring.studycafe.applcation.studycafe.seat.SeatInfo;
import io.spring.studycafe.applcation.studycafe.seat.SeatService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "좌석")
@RequestMapping(value = "/api/v1/study-cafe-seats")
@RestController
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public ResponseEntity<List<SeatResponse>> findAll(@RequestParam(value = "studyCafeId") Long id) {
        List<SeatResponse> responses = seatService.findAll(id)
            .stream().map(SeatResponse::new)
            .toList();

        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{seatId}/use")
    public ResponseEntity<SeatUsageResponse> useSeat(
        @PathVariable(value = "seatId") Long seatId,
        @Parameter(hidden = true) @Authorization AuthorizationInfo authorizationInfo
    ) {
        SeatInfo seatInfo = seatService.useSeat(seatId, authorizationInfo.id());

        return ResponseEntity.ok(new SeatUsageResponse(seatInfo));
    }

    @PatchMapping("/{seatId}/leave")
    public ResponseEntity<SeatLeaveResponse> leaveSeat(
        @PathVariable(value = "seatId") Long seatId,
        @RequestParam(value = "studyCafeId") Long studyCafeId,
        @Parameter(hidden = true) @Authorization AuthorizationInfo authorizationInfo
    ) {
        SeatInfo seatInfo = seatService.leaveSeat(studyCafeId, seatId, authorizationInfo.id());

        return ResponseEntity.ok(new SeatLeaveResponse(seatInfo));
    }
}

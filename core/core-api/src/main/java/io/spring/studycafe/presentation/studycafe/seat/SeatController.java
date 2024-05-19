package io.spring.studycafe.presentation.studycafe.seat;

import io.spring.studycafe.applcation.studycafe.seat.SeatInfo;
import io.spring.studycafe.applcation.studycafe.seat.SeatService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/seats")
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

//    @PatchMapping("/{seatId}/use")
//    public ResponseEntity<SeatResponse> useSeat(
//        @PathVariable(value = "seatId") Long seatId,
//        @Authorization AuthorizationInfo authorizationInfo
//    ) {
//        SeatInfo seatInfo = seatService.useSeat(seatId, authorizationInfo.id());
//
//        return ResponseEntity.ok(new SeatResponse(seatInfo));
//    }
//
//    @PatchMapping("/{seatId}/leave")
//    public ResponseEntity<SeatResponse> leaveSeat(
//        @PathVariable(value = "seatId") Long seatId,
//        @Authorization AuthorizationInfo authorizationInfo
//    ) {
//        SeatInfo seatInfo = seatService.leaveSeat(seatId, authorizationInfo.id());
//
//        return ResponseEntity.ok(new SeatResponse(seatInfo));
//    }
}

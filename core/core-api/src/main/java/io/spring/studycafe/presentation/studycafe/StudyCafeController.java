package io.spring.studycafe.presentation.studycafe;

import io.spring.studycafe.applcation.studycafe.StudyCafeInfo;
import io.spring.studycafe.applcation.studycafe.StudyCafeRegistrationCommand;
import io.spring.studycafe.applcation.studycafe.StudyCafeService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "스터디카페")
@RequestMapping("/api/v1/study-cafes")
@RestController
public class StudyCafeController {

    private final StudyCafeService studyCafeService;

    public StudyCafeController(StudyCafeService studyCafeService) {
        this.studyCafeService = studyCafeService;
    }

    @PostMapping
    public ResponseEntity<StudyCafeResponse> register(
        @Parameter(hidden = true) @Authorization AuthorizationInfo authorizationInfo,
        @RequestBody @Valid StudyCafeRegistrationRequest request
    ) {
        StudyCafeInfo studyCafeInfo = studyCafeService.register(new StudyCafeRegistrationCommand(
            request.name(),
            request.address(),
            request.phoneNumber(),
            authorizationInfo.id())
        );

        StudyCafeResponse response = new StudyCafeResponse(
            studyCafeInfo.id(),
            studyCafeInfo.name(),
            studyCafeInfo.address(),
            studyCafeInfo.phoneNumber(),
            studyCafeInfo.memberId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudyCafeResponse>> findAll(@Parameter(hidden = true) @Authorization AuthorizationInfo authorizationInfo) {
        List<StudyCafeResponse> responses = studyCafeService.findAllByMemberId(authorizationInfo.id())
            .stream()
            .map(studyCafeInfo -> new StudyCafeResponse(
                studyCafeInfo.id(),
                studyCafeInfo.name(),
                studyCafeInfo.address(),
                studyCafeInfo.phoneNumber(),
                studyCafeInfo.memberId()
            ))
            .toList();

        return ResponseEntity.ok(responses);
    }
}

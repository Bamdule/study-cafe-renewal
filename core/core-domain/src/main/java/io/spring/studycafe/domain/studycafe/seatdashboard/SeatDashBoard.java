package io.spring.studycafe.domain.studycafe.seatdashboard;

import io.spring.studycafe.domain.common.TimeInfo;
import io.spring.studycafe.domain.studycafe.customerticket.CustomerTicket;

import java.time.LocalDateTime;

public class SeatDashBoard {
    private Long id;
    private Long customerId;
    private CustomerTicket customerTicket;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private TimeInfo getUsedTimeInfo() {
        return TimeInfo.createElapsedTimeInfo(startDateTime, LocalDateTime.now());
    }

}

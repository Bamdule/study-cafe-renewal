package io.spring.studycafe.presentation.studycafe.order;

import io.spring.studycafe.applcation.order.OrderInfo;
import io.spring.studycafe.applcation.order.OrderService;
import io.spring.studycafe.config.authorization.Authorization;
import io.spring.studycafe.config.authorization.AuthorizationInfo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "고객 주문")
@RequestMapping(value = "/api/v1/study-cafe-orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(
        @Parameter(hidden = true) @Authorization AuthorizationInfo authorizationInfo,
        @RequestParam(value = "studyCafeId") Long studyCafeId) {

        List<OrderInfo> orderInfos = orderService.findAll(studyCafeId, authorizationInfo.id());

        return ResponseEntity.ok(OrderResponse.createList(orderInfos));
    }

}

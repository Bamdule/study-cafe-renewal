package io.spring.studycafe.applcation.payment.ticket;

import io.spring.studycafe.domain.order.orderitem.OrderItem;
import io.spring.studycafe.domain.order.orderitem.OrderItemFactory;
import io.spring.studycafe.domain.order.orderitem.OrderItemType;
import io.spring.studycafe.domain.studycafe.ticket.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketOrderItemFactory implements OrderItemFactory<Ticket> {
    @Override
    public OrderItem create(Ticket item) {
        return new OrderItem(item.getId(), item.getName(), item.getPrice(), OrderItemType.TICKET, 1);
    }

    @Override
    public OrderItemType getOrderItemType() {
        return OrderItemType.TICKET;
    }
}

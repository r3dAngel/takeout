package com.hohai.take_out.component;

import com.hohai.take_out.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CancelOrderRecriver {
    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "takeout.order.cancel")
    public void handle(Long orderId) {
        orderService.cancelOrder(orderId);
        System.out.println("receive delay message orderId:{" + orderId + "}");
    }
}

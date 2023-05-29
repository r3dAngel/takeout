package com.hohai.take_out.component;

import com.hohai.take_out.entity.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CancelOrderSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Long orderId, final long delayTimes) {
        amqpTemplate.convertAndSend(QueueEnum.ORDER_TTL_QUEUE.getExchangeName(), QueueEnum.ORDER_TTL_QUEUE.getRouteKey(), orderId, message -> {
            message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
            return message;
        });
        log.info("send delay message orderId:{}", orderId);
    }

}

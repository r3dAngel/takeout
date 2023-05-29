package com.hohai.take_out.config;

import com.hohai.take_out.entity.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.ORDER_CANCEL_QUEUE.getQueueName());
    }

    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.ORDER_TTL_QUEUE.getQueueName())
                .withArgument("x-dead-letter-exchange", QueueEnum.ORDER_CANCEL_QUEUE.getExchangeName())
                .withArgument("x-dead-letter-routing-key", QueueEnum.ORDER_CANCEL_QUEUE.getRouteKey())
                .build();
    }

    @Bean
    public DirectExchange orderDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.ORDER_CANCEL_QUEUE.getExchangeName())
                .durable(true)
                .build();
    }

    @Bean
    public DirectExchange orderTtlDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.ORDER_TTL_QUEUE.getExchangeName())
                .durable(true)
                .build();
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder
                .bind(orderQueue())
                .to(orderDirect())
                .with(QueueEnum.ORDER_CANCEL_QUEUE.getRouteKey());
    }

    @Bean
    public Binding orderTtlBinding() {
        return BindingBuilder
                .bind(orderTtlQueue())
                .to(orderTtlDirect())
                .with(QueueEnum.ORDER_TTL_QUEUE.getRouteKey());
    }
}

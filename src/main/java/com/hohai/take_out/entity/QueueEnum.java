package com.hohai.take_out.entity;

import lombok.Getter;

@Getter
public enum QueueEnum {
    ORDER_CANCEL_QUEUE("takeout.order.direct", "takeout.order.cancel", "takeout.order.cancel"),
    ORDER_TTL_QUEUE("takeout.order.ttl.direct", "takeout.order.cancel.ttl", "takeout.order.cancel.ttl");
    private String ExchangeName;
    private String QueueName;
    private String RouteKey;

    QueueEnum(String ExchangeName, String QueueName, String RouteKey) {
        this.ExchangeName = ExchangeName;
        this.QueueName = QueueName;
        this.RouteKey = RouteKey;
    }
}

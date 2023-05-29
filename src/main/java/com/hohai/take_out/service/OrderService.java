package com.hohai.take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hohai.take_out.entity.Orders;

import java.util.List;

public interface OrderService extends IService<Orders> {
    public void submit(Orders orders);

    public void cancelOrder(Long id);

}

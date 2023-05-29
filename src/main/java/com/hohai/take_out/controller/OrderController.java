package com.hohai.take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hohai.take_out.common.R;
import com.hohai.take_out.dto.OrderDto;
import com.hohai.take_out.entity.Orders;
import com.hohai.take_out.entity.User;
import com.hohai.take_out.service.OrderService;
import com.hohai.take_out.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        log.info("订单分页查询：page={},pageSize={}",page,pageSize);
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        Page<OrderDto> orderDtoPage = new Page<>();
        LambdaQueryWrapper<Orders> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.orderByDesc(Orders::getOrderTime);
        orderService.page(pageInfo,lambdaQueryWrapper);
        BeanUtils.copyProperties(pageInfo,orderDtoPage,"records");
        List<Orders> ordersList = pageInfo.getRecords();

        List<OrderDto> orderDtoList = ordersList.stream().map((item)->{
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(item,orderDto);
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(User::getId,item.getId());
            User user = userService.getOne(queryWrapper);
            orderDto.setUserId(user.getId());
            return orderDto;
        }).collect(Collectors.toList());
        orderDtoPage.setRecords(orderDtoList);
        return R.success(orderDtoPage);
    }
}
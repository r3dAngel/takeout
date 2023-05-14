package com.hohai.take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hohai.take_out.dto.DishDto;
import com.hohai.take_out.entity.Dish;

public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);
}

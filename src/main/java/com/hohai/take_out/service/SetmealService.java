package com.hohai.take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hohai.take_out.common.R;
import com.hohai.take_out.dto.SetmealDto;
import com.hohai.take_out.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);
    public SetmealDto getByIdWithDish(Long id);
    public R<String> updateWithDish(SetmealDto setmealDto);
}

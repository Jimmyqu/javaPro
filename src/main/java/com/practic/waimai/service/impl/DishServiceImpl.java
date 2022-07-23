package com.practic.waimai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practic.waimai.entity.Dish;
import com.practic.waimai.mapper.DishMapper;
import com.practic.waimai.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

}

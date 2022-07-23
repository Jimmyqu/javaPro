package com.practic.waimai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practic.waimai.common.CustException;
import com.practic.waimai.entity.Category;
import com.practic.waimai.entity.Dish;
import com.practic.waimai.entity.Setmeal;
import com.practic.waimai.mapper.CategoryMapper;
import com.practic.waimai.service.CategoryService;
import com.practic.waimai.service.DishService;
import com.practic.waimai.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(@RequestBody Category cate) {
        Long id = cate.getId();
        log.info("id是 {}", id);
        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper();
        // entity 只是获取字段用
        dishWrapper.eq(Dish::getCategoryId, id);
        // 实际查询还是对应Service
        int count1 = dishService.count(dishWrapper);

        LambdaQueryWrapper<Setmeal> setmealWrapper = new LambdaQueryWrapper();
        // entity 只是获取字段用
        setmealWrapper.eq(Setmeal::getCategoryId, id);
        // 实际查询还是对应Service
        int count2 = setmealService.count(setmealWrapper);

        log.info("count1数量为 {}", count1);
        if(count1 > 0) {
            throw new CustException("已经关联菜品，不能删除");
        }

        if(count2 > 0) {
            throw new CustException("已经关联套餐，不能删除");
        }

        super.removeById(id);
    }
}

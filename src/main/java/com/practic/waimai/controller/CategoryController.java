package com.practic.waimai.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.practic.waimai.entity.Category;
import com.practic.waimai.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import com.practic.waimai.common.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result getPage(int page, int pageSize) {
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper();
        wrapper.orderByAsc(Category::getSort);

        categoryService.page(pageInfo, wrapper);
        return Result.success(pageInfo);
    };

    @PostMapping("/save")
    public Result save(@RequestBody Category cate) {
        categoryService.save(cate);
        return Result.success("添加成功");
    };

    @PostMapping("/del")
    public Result del(@RequestBody Category cate) {
        categoryService.remove(cate);
        return Result.success("删除成功");
    };
}

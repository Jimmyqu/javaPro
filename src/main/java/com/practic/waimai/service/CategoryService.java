package com.practic.waimai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.practic.waimai.entity.Category;
import org.springframework.web.bind.annotation.RequestBody;

public interface CategoryService extends IService<Category> {
    public void remove(@RequestBody Category cate);
}

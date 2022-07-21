package com.practic.waimai.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  MybatisPlus分页插件
 */
@Configuration
public class MybatisPlusConfig {

    @Bean // 指定由springframework 管理
    public MybatisPlusInterceptor interceptor() {
      MybatisPlusInterceptor i = new MybatisPlusInterceptor();
      i.addInnerInterceptor(new PaginationInnerInterceptor());
      return i;
    };
}

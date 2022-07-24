package com.practic.waimai.controller;

import com.practic.waimai.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/common")
public class Common {

    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        // 请求中的file是一个临时文件 请求结束就删除 需要转存到实际物理地址或者oss
        String originalFilename = file.getOriginalFilename();

        String uid = UUID.randomUUID().toString();

        String fileName = uid + "." + originalFilename.substring(originalFilename.lastIndexOf("."));

        try {
            // TODO 待使用7牛云 或者basePath处理
            file.transferTo(new File("D:\\"+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    };
}

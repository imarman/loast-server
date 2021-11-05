package com.loast.controller;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Arman
 */
@RestController
@Slf4j
public class TestController {

    public String test() {
        StrUtil.format("asdasd", "sdasd");
        return null;
    }

}

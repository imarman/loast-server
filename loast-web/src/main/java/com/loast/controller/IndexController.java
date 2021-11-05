package com.loast.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.loast.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Arman
 * @date 2021/11/5 12:18
 */
@RestController
@RequestMapping("index")
public class IndexController {

    @SaCheckLogin
    @GetMapping
    public R index() {
        return R.ok("欢迎来到首页");
    }
}

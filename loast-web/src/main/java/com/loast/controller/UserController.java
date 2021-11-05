package com.loast.controller;


import com.loast.model.UserModel;
import com.loast.result.R;
import com.loast.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户(User)表控制层
 *
 * @author Arman
 * @since 2021-11-03 21:14:12
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/register")
    public R registerUser(@RequestBody @Validated UserModel userModel, HttpServletRequest request) {
        log.info("用户注册接口：userModel:{}", userModel);
        userService.register(userModel, request);
        return R.ok("注册成功，请注意邮箱的激活地址~");
    }

    @GetMapping("active")
    public R active(@RequestParam("code") String code) {
        userService.activeUser(code);
        return R.ok();
    }



}


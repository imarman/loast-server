package com.loast.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.loast.model.UserModel;
import com.loast.result.R;
import com.loast.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Arman
 * @date 2021/11/5 11:38
 */
@RestController
@Slf4j
public class LoginController {

    @Resource
    ILoginService loginService;

    @PostMapping("/login")
    public R login(@RequestBody UserModel userModel) {
        if (StpUtil.isLogin()) {
            return R.ok("该用户已经登陆");
        }
        loginService.login(userModel);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return R.ok(tokenInfo);
    }

    @GetMapping("/logout")
    public R logout() {
        if (!StpUtil.isLogin()) {
            return R.ok("请先登陆~");
        }
        StpUtil.logout();
        return R.ok();
    }

}

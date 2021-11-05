package com.loast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loast.entity.User;
import com.loast.model.UserModel;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户(User)表服务接口
 *
 * @author Arman
 * @since 2021-11-03 21:14:19
 */
public interface IUserService extends IService<User> {

    /**
     * 用户注册
     * @param userModel 用户对象
     * @param request 请求对象
     */
    void register(UserModel userModel, HttpServletRequest request);

    /**
     * 根据 code 激活用户
     * @param code 激活码
     */
    void activeUser(String code);
}


package com.loast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loast.entity.User;
import com.loast.model.UserModel;

/**
 * 用户登陆服务
 *
 * @author Arman
 * @date 2021/11/5 11:44
 */
public interface ILoginService extends IService<User> {

    /**
     * 用户登陆
     * @param userModel 用户对象
     */
    void login(UserModel userModel);

}

package com.loast.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loast.conn.ResultCodeEnum;
import com.loast.conn.UserStatus;
import com.loast.entity.User;
import com.loast.exception.BusinessException;
import com.loast.mapper.UserMapper;
import com.loast.model.UserModel;
import com.loast.service.ILoginService;
import com.loast.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Arman
 * @date 2021/11/5 11:44
 */
@Service
@Slf4j
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements ILoginService {

    @Override
    public void login(UserModel userModel) {
        if (StrUtil.isEmpty(userModel.getEmail()) || StrUtil.isEmpty(userModel.getPassword())) {
            log.info("参数缺失- userModel:{}", userModel);
            throw new BusinessException(ResultCodeEnum.LOGIN_FAIL, "邮箱或密码不能为空");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, userModel.getEmail());
        User user = getBaseMapper().selectOne(queryWrapper);
        if (user == null) {
            log.info("用户不存在");
            throw new BusinessException(ResultCodeEnum.USER_NOT_EXIST);
        }
        // 判断用户是否已经激活
        if (UserStatus.INACTIVE.getCode().equals(user.getStatus())) {
            log.info("用户:{}({}) 尚未激活,拒绝登陆", user.getUsername(), user.getId());
            throw new BusinessException(ResultCodeEnum.USER_INACTIVE);
        }

        // 拿到数据库用户的盐值，和输入的密码做加密后对比
        String salt = user.getSalt();
        String encryptPwd = Md5Utils.encrypt(userModel.getPassword(), salt);
        // 账号不匹配
        if (!StrUtil.equals(encryptPwd, user.getPassword())) {
            log.error("用户:{} 输入密码错误", user.getUsername());
            throw new BusinessException(ResultCodeEnum.BAD_PASSWORD);
        }
        // 账号匹配
        StpUtil.login(user.getId());

        // 存入到 session 时要屏蔽敏感数据
        user.setPassword(null);
        user.setSalt(null);
        // 把用户存到 session 中，方便取出用户信息
        StpUtil.getSession().set("user", user);
    }

}

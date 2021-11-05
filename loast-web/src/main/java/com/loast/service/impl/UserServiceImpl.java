package com.loast.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loast.conn.ResultCodeEnum;
import com.loast.conn.UserStatus;
import com.loast.entity.Email;
import com.loast.entity.User;
import com.loast.exception.BusinessException;
import com.loast.mapper.UserMapper;
import com.loast.model.UserModel;
import com.loast.service.IUserService;
import com.loast.task.MailTask;
import com.loast.utils.GenerateUtils;
import com.loast.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户(User)表服务实现类
 *
 * @author Arman
 * @since 2021-11-03 22:14:19
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    MailTask mailTask;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserModel userModel, HttpServletRequest request) {
        if (userModel == null) {
            log.error("用户对象为空");
            throw new BusinessException(ResultCodeEnum.REGISTER_ERROR);
        }
        // 如果缺少用户名、密码、邮箱就抛异常
        if (StrUtil.isEmpty(userModel.getUsername()) || StrUtil.isEmpty(userModel.getPassword()) || StrUtil.isEmpty(userModel.getEmail())) {
            throw new BusinessException(ResultCodeEnum.REGISTER_ERROR, "注册对象缺少参数");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, userModel.getEmail());
        User user = getBaseMapper().selectOne(queryWrapper);
        if (null != user) {
            log.error("该邮箱:{} 已被用户:{}注册", user.getEmail(), user.getId());
            throw new BusinessException(ResultCodeEnum.REGISTER_ERROR, "邮箱已被注册");
        }

        // 用户的数据保存入库
        User registerUser = new User();
        BeanUtil.copyProperties(userModel, registerUser);
        registerUser.setId(GenerateUtils.generateId());
        registerUser.setActiveCode(GenerateUtils.makeActiveCode());
        String salt = GenerateUtils.generateSalt();
        String originPwd = registerUser.getPassword();
        String encryptPwd = Md5Utils.encrypt(originPwd, salt);
        registerUser.setSalt(salt);
        registerUser.setPassword(encryptPwd);
        registerUser.setRegTime(new Date());
        registerUser.setStatus(UserStatus.INACTIVE.getCode());
        registerUser.setRegIp(request.getRemoteAddr());
        save(registerUser);

        // 发送邮件
        Email email = new Email();
        email.setTo(registerUser.getEmail());
        email.setSubject("账号激活邮件");
        email.setUsername(registerUser.getUsername());
        // 拼接激活地址
        String activeUrl = StrUtil.format("{}://{}:{}/api/user/active?code={}", request.getScheme(), request.getServerName(), request.getServerPort(), registerUser.getActiveCode());
        String content = StrUtil.format("<h3>此邮件为激活邮件！请点击下面链接完成激活操作！</h3> <a href=\"{}\">激活请点击:{}</a> ", activeUrl, registerUser.getActiveCode());
        email.setActiveUrl(activeUrl);
        email.setContent(content);
        mailTask.sendMail(email);

        log.info("用户注册成功 user:{}", registerUser);
    }

    @Override
    public void activeUser(String code) {
        if (StrUtil.isEmpty(code)) {
            log.error("激活码为空");
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "激活码为空");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getActiveCode, code);
        User user = getBaseMapper().selectOne(queryWrapper);
        if (null == user) {
            log.info("激活码为:{} 的用户不存在", code);
            throw new BusinessException(ResultCodeEnum.REGISTER_ERROR, "用户不存在");
        }
        // 如果用户存在
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(User::getActiveCode, null)
                .set(User::getStatus, 0)
                .eq(User::getId, user.getId());
        getBaseMapper().update(user, wrapper);

        log.info("用户:{}({}) 激活成功", user.getUsername(), user.getId());
    }
}


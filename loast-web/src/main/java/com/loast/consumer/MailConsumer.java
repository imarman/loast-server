package com.loast.consumer;

import cn.hutool.core.util.StrUtil;
import com.loast.config.RabbitMQConfig;
import com.loast.entity.Email;
import com.loast.entity.SendEmailModel;
import com.loast.entity.User;
import com.loast.task.MailTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Arman
 * @date 2021/11/19 18:54
 */
@Component
@Slf4j
public class MailConsumer {

    @Resource
    MailTask mailTask;


    @RabbitListener(queues = RabbitMQConfig.SEND_EMAIL_QUEUE_NAME)
    public void receive(SendEmailModel emailModel) {
        User registerUser = emailModel.getUser();
        log.info("消息队列：{}, 接收到消息：{}", RabbitMQConfig.SEND_EMAIL_QUEUE_NAME, registerUser);
        try {
            Email email = new Email();
            email.setTo(registerUser.getEmail());
            email.setSubject("账号激活邮件");
            email.setUsername(registerUser.getUsername());
            // 拼接激活地址
            String content = StrUtil.format("<h3>此邮件为激活邮件！请点击下面链接完成激活操作！</h3> <a href=\"{}\">激活请点击:{}</a> ", emailModel.getActiveUrl(), registerUser.getActiveCode());
            email.setActiveUrl(emailModel.getActiveUrl());
            email.setContent(content);
            mailTask.sendMail(email);
            log.info("消息队列处理成功");
        } catch (Exception e) {
            log.error("给用户:{}({}), 发送邮件失败！:{}", registerUser.getUsername(), registerUser.getId(), e.getMessage());
            e.printStackTrace();
        }

    }
}

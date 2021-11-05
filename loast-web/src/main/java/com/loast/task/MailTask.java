package com.loast.task;

import com.loast.conn.ResultCodeEnum;
import com.loast.entity.Email;
import com.loast.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 发送邮件的类
 *
 * @author Arman
 * @date 2021/11/4 22:14
 */
@Component
@Slf4j
public class MailTask {

    @Resource
    JavaMailSender javaMailSender;

    @Resource
    MailProperties mailProperties;

    public void sendMail(Email email) {
        // 收到消息，发送邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            helper.setTo(email.getTo());
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject(email.getSubject());
            helper.setSentDate(new Date());
            helper.setText(email.getContent(), true);
            javaMailSender.send(msg);
            log.info("给用户{}({}) 发送了激活邮件, activeUrl:{}", email.getUsername(), email.getTo(), email.getActiveUrl());
        } catch (MessagingException e) {
            log.error("给用户{} 发送了激活邮件失败", email.getTo());
            throw new BusinessException(ResultCodeEnum.EMAIL_SEND_ERROR);
        }
    }


}
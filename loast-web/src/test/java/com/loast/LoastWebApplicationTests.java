package com.loast;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.AES;
import com.loast.entity.Email;
import com.loast.task.MailTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoastWebApplicationTests {

    @Test
    void contextLoads() {

        // // 生成 16 位随机 AES 密钥
        String randomKey = "xxxxx";
        System.out.println("randomKey = " + randomKey);
        // 随机密钥加密
        String url = AES.encrypt("xxxxxx", randomKey);
        System.out.println("url = " + url);
        String username = AES.encrypt("xxx", randomKey);
        System.out.println(username);
    }

    @Autowired
    MailTask mailTask;

    @Test
    public void sendMail() {
        // Email email = new Email();
        // email.setAddress("xxxx");
        // email.setActiveCode("sdfsdfsdfsdf");
        // email.setUsername("alex");
        // email.setContent("http://sadasdasdasdasdasda/sadasdasdasdasd");
        // this.mailTask.sendMail(email);

    }

}

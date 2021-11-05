package com.loast;


import cn.hutool.core.util.IdUtil;
import com.loast.entity.Email;
import com.loast.task.MailTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoastWebApplicationTests {

    @Test
    void contextLoads() {


        System.out.println("IdUtil.fastSimpleUUID() = " + IdUtil.fastSimpleUUID());

        System.out.println("IdUtil.getSnowflake().nextIdStr() = " + IdUtil.getSnowflake().nextIdStr());
        System.out.println("IdUtil.getSnowflake().nextIdStr() = " + IdUtil.getSnowflake().nextIdStr());
        System.out.println("IdUtil.getSnowflake().nextIdStr() = " + IdUtil.getSnowflake().nextIdStr());
        System.out.println("IdUtil.getSnowflake().nextIdStr() = " + IdUtil.getSnowflake().nextIdStr());
        System.out.println("IdUtil.getSnowflake().nextIdStr() = " + IdUtil.getSnowflake().nextIdStr());

        // // 生成 16 位随机 AES 密钥
        // String randomKey = "loast-project-v1";
        // System.out.println("randomKey = " + randomKey);
        // // 随机密钥加密
        // String url = AES.encrypt("jdbc:mysql://47.97.18.175:3306/loast?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true", randomKey);
        // System.out.println("url = " + url);
        // String username = AES.encrypt("root", randomKey);
        // System.out.println("username = " + username);
        // String pwd = AES.encrypt("penghuiDB01", randomKey);
        // System.out.println("pwd = " + pwd);

    }

    @Autowired
    MailTask mailTask;

    @Test
    public void sendMail() {
        // Email email = new Email();
        // email.setAddress("arman503@163.com");
        // email.setActiveCode("sdfsdfsdfsdf");
        // email.setUsername("alex");
        // email.setContent("http://sadasdasdasdasdasda/sadasdasdasdasd");
        // this.mailTask.sendMail(email);
    }

}

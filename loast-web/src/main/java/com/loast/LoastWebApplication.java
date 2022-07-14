package com.loast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Arman
 */
@SpringBootApplication()
public class LoastWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoastWebApplication.class, args);
        // System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }

}

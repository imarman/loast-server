package com.loast.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Arman
 * @date 2021/11/19 19:53
 */
@Configuration
public class RabbitMQConfig {
    public static final String SEND_EMAIL_QUEUE_NAME = "send_email_queue";

    @Bean
    Queue emailQueue() {
        return new Queue(SEND_EMAIL_QUEUE_NAME);
    }
}

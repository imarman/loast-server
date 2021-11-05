package com.loast.entity;

import lombok.Data;

/**
 * @author Arman
 * @date 2021/11/4 22:49
 */
@Data
public class Email {

    private String from;

    private String to;

    private String username;

    private String subject;

    private String content;

    private String activeUrl;

}

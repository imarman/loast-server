package com.loast.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 发送邮件的实体
 *
 * @author Arman
 * @date 2021/11/19 21:05
 */
@Data
public class SendEmailModel implements Serializable {

    private static final long serialVersionUID = -497548768079041459L;

    private User user;
    private String activeUrl;

}

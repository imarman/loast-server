package com.loast.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Arman
 */
@Data
@ToString
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * 用户密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * Email
     */
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 用户手机
     */
    private String mobile;

    /**
     * 头像文件
     */
    private String avatarFile;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 注册时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date regTime;

    /**
     * 注册IP
     */
    private Long regIp;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
}

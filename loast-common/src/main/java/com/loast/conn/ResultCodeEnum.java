package com.loast.conn;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Arman
 */
@Getter
@ToString
public enum ResultCodeEnum {

    SUCCESS(true, 99999, "成功"),
    UNKNOWN_REASON(false, 20001, "未知错误"),

    BAD_SQL_GRAMMAR(false, 21001, "sql 语法错误"),
    JSON_PARSE_ERROR(false, 21002, "json 解析异常"),
    PARAM_ERROR(false, 21003, "参数不正确"),

    FILE_UPLOAD_ERROR(false, 21004, "文件上传错误"),
    FILE_DELETE_ERROR(false, 21005, "文件刪除错误"),

    FETCH_ACCESSTOKEN_FAILD(false, 23003, "获取 accessToken 失败"),
    FETCH_USERINFO_ERROR(false, 23004, "获取用户信息失败"),
    REGISTER_ERROR(false, 23006, "注册失败"),

    CODE_ERROR(false, 28000, "验证码错误"),

    LOGIN_PHONE_ERROR(false, 28009, "手机号码不正确"),


    LOGIN_DISABLED_ERROR(false, 28002, "该用户已被禁用"),
    REGISTER_MOBILE_ERROR(false, 28003, "手机号已被注册"),

    LOGIN_AUTH(false, 28004, "需要登录"),
    LOGIN_ACL(false, 28005, "没有权限"),
    EMAIL_SEND_ERROR(false, 28006, "邮件发送失败"),
    USER_INACTIVE(false, 28008, "请激活后账号后登陆"),

    LOGIN_FAIL(false, 30000, "用户名或密码错误"),
    USER_NOT_EXIST(false, 30001, "用户不存在"),
    BAD_PASSWORD(false, 30002, "账号或密码错误"),

    ;


    private final Boolean success;

    private final Integer code;

    private final String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}

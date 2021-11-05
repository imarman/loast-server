package com.loast.conn;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Arman
 */
@Getter
@ToString
public enum UserStatus {

    NORMAL("normal", 0),
    INACTIVE("inactive", 1),
    FROZEN("frozen", 2),
    ;

    private final Integer code;
    private final String message;

    UserStatus(String message, Integer code) {
        this.code = code;
        this.message = message;
    }
}

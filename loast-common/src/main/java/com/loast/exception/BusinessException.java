package com.loast.exception;

import com.loast.conn.ResultCodeEnum;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Arman
 */
@Getter
@ToString
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 5227069401135182444L;

    private ResultCodeEnum resultCodeEnum;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
    }

    public BusinessException(ResultCodeEnum resultCodeEnum, String message) {
        super(message);
        this.resultCodeEnum = resultCodeEnum;
    }

}

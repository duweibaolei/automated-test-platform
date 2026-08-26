package com.dwl.common.exception;


import com.dwl.common.enums.ErrorCode;
import lombok.Getter;

import java.io.Serial;

/**
 * 业务异常
 * <p>
 * Business Exception
 * <p>
 * 业务逻辑中抛出的受检异常, 携带 ErrorCode 枚举以标识具体错误类型
 * <p>
 * Runtime exception thrown during business logic, carrying an ErrorCode enum
 * to identify the specific error type
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-10 01:31
 */
@Getter
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码枚举
     * Error code enum
     */
    private final ErrorCode errorCode;


    public int getCode() {
        return errorCode.getCode();
    }

    /**
     * 使用 ErrorCode 枚举构造业务异常
     * Construct business exception with ErrorCode enum
     *
     * @param errorCode Error code enum
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    /**
     * 使用 ErrorCode 枚举和自定义消息构造业务异常
     * Construct business exception with ErrorCode enum and custom message
     *
     * @param errorCode Error code enum
     * @param message   Custom error message
     */
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 使用 ErrorCode 枚举和原因异常构造业务异常
     * Construct business exception with ErrorCode enum and cause
     *
     * @param errorCode Error code enum
     * @param cause     Cause exception
     */
    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    /**
     * 使用 ErrorCode 枚举 / 自定义消息和原因异常构造业务异常
     * Construct business exception with ErrorCode enum, custom message, and cause
     *
     * @param errorCode Error code enum
     * @param message   Custom error message
     * @param cause     Cause exception
     */
    public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}

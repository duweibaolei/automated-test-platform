package com.dwl.common.result;


import com.dwl.common.enums.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一响应体
 * Unified API Response Body
 * <p>
 * 所有 API 接口的统一返回值的统一包装类,包含状态码 消息 数据和时间戳
 * Unified wrapper for all API response values,
 * containing status code message data and timestamp
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-01 20:47
 */
@Data
@Schema(description = """
        统一响应体
        Unified API Response Body
        """)
public class R<T> implements Serializable {

    /**
     * Serial version UID
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 响应状态码,详见 {@link ErrorCode}
     */
    @Schema(description = """
            响应状态码
            Response Status Code
            """, example = "200")
    private int code;

    @Schema(description = """
            响应消息
            Response message
            """, example = "操作成功")
    private String message;

    @Schema(description = """
            响应数据
            Response data
            """)
    private T data;

    @Schema(description = """
            响应时间戳
            Response timestamp
            """, example = "1704067200000")
    private long timestamp;

    /**
     * 默认构造函数
     * Default constructor - initializes timestamp to current time
     */
    public R() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 全参构造函数
     * Full argument constructor
     *
     * @param code    Status Code
     * @param message Message
     * @param data    Data
     */
    public R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }


    /*
     * ==================== 成功响应工厂方法 ====================
     * ==================== Success Factory Methods ====================
     * */

    /**
     * 返回成功响应(无数据)
     * Return success response without data
     *
     * @param <T> Data generic type
     * @return Success response body
     */
    public static <T> R<T> ok() {
        return new R<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
    }

    /**
     * Return success response with data
     *
     * @param data Response data
     * @param <T>  Data generic type
     * @return Success response body
     */
    public static <T> R<T> ok(T data) {
        return new R<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }


    /**
     * 返回成功响应(自定义消息和数据)
     * Return success response with custom message and data
     *
     * @param data    Custom data
     * @param message Response data
     * @param <T>     Data generic type
     * @return Success response
     */
    public static <T> R<T> ok(T data, String message) {
        return new R<>(ErrorCode.SUCCESS.getCode(), message, data);
    }

    /*
     * ==================== 失败响应工厂方法 ====================
     * ==================== Failure Factory Methods ====================
     * */

    /**
     * 返回失败响应(默认内部错误)
     * Return failure response with default internal error
     *
     * @param <T> Data generic type
     * @return Failure response body
     */
    private static <T> R<T> fail() {
        return new R<>(ErrorCode.INTERNAL_ERROR.getCode(), ErrorCode.INTERNAL_ERROR.getMessage(), null);
    }

    /**
     * 返回失败响应(使用ErrorCode枚举)
     * Return failure response using ErrorCode enum
     *
     * @param errorCode Error code enum
     * @param <T>       Data generic type
     * @return Failure response body
     */
    public static <T> R<T> fail(ErrorCode errorCode) {
        return new R<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 返回失败响应(使用ErrorCode枚举和自定义消息)
     * Return failure response using ErrorCode enum with custom message
     *
     * @param errorCode Error code enum
     * @param message   Custom error message
     * @param <T>       Data generic type
     * @return Failure response body
     */
    public static <T> R<T> fail(ErrorCode errorCode, String message) {
        return new R<>(errorCode.getCode(), message, null);
    }

}

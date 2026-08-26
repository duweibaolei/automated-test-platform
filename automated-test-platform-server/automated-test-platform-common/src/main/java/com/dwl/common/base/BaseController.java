package com.dwl.common.base;


import com.dwl.common.enums.ErrorCode;
import com.dwl.common.result.PageResult;
import com.dwl.common.result.R;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 基础控制器
 * <p>
 * Base Controller
 * <p>
 * 所有控制器的公共父类, 提供统一的响应构建方法和当前登陆用户的信息获取
 * <p>
 * Common parent class for all controller, providing unified response building
 * methods and current login user information retrieval
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-01 17:30
 */
@Schema(description = """
        基础控制器
        Base Controller
        """)
public abstract class BaseController {

    /* ==================== 成功响应构建方法 ====================
     * ==================== Success Response Builder Methods ====================
     * */

    /**
     * 返回成功响应(无数据)
     * Return success response with data
     *
     * @param <T> Data generic type
     * @return Success response body
     */
    protected <T> R<T> success() {
        return R.ok();
    }

    /**
     * 返回成功响应(带数据)
     * Return success response with data
     *
     * @param data Response data
     * @param <T>  Data generic type
     * @return Success response body
     */
    protected <T> R<T> success(T data) {
        return R.ok(data);
    }

    /**
     * Return success response with paginated data
     *
     * @param page Paginated result
     * @param <T>  Data generic type
     * @return Success response body
     */
    protected <T> R<PageResult<T>> success(PageResult<T> page) {
        return R.ok(page);
    }

    /* ==================== 失败响应构建方法 ====================
     * ==================== Failure Response Builder Methods ====================
     * */

    /**
     * 返回失败的响应(使用 ErrorCode 枚举)
     * Return failure response using ErrorCode enum
     *
     * @param errorCode Error code enum
     * @param <T>       Data generic type
     * @return failure response body
     */
    protected <T> R<T> fail(ErrorCode errorCode) {
        return R.fail(errorCode);
    }

    /**
     * Return failure response with custom message
     *
     * @param message Error message
     * @param <T>     Data generic type
     * @return Failure response body
     */
    protected <T> R<T> fail(String message) {
        return R.fail(ErrorCode.INTERNAL_ERROR, message);
    }

}

package com.dwl.common.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举
 * <p>
 * Error Code Enumeration
 * <p>
 * 定义系统中的所有的错误码, 包含通用 HTTP 错误码和业务领域错误码
 * <p>
 * Defines all error codes in the system, including generic HTTP error codes
 * and domain-specific business error codes
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-01 22:08
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        错误码枚举
        Error Code Enumeration
        """)
public enum ErrorCode {

    /*
     * =============================== 通用错误码 ===============================
     * =============================== Generic HTTP Error Codes ===============================
     */

    /**
     * Operation successful
     */
    SUCCESS(200, "操作成功"),

    /**
     * 请求参数错误
     * <p>
     * Bad request - invalid parameters
     */
    BAD_REQUEST(400, "请求参数错误"),

    /**
     * 未认证
     * <p>
     * Unauthorized - not authenticated
     */
    UNAUTHORIZED(401, "未认证"),

    /**
     * 无权限
     * <p>
     * Forbidden - insufficient permissions
     */
    FORBIDDEN(403, "无权限"),

    /**
     * 资源不存在
     * <p>
     * Resource not found
     */
    NOT_FOUND(404, "资源不存在"),

    /**
     * 数据冲突
     * <p>
     * Data conflict
     */
    CONFLICT(409, "数据冲突"),

    /**
     * 服务器内部错误
     * <p>
     * Internal server error
     */
    INTERNAL_ERROR(500, "服务器内部错误"),

    /**
     * 上游服务不可用
     * <p>
     * Upstream service unavailable
     */
    UPSTREAM_UNAVAILABLE(502, "上游服务不可用"),

    /**
     * 服务暂不可用
     * <p>
     * Service temporarily unavailable
     */
    SERVICE_UNAVAILABLE(503, "服务暂不可用"),

    /*
     * =============================== 用户模块错误码 ===============================
     * =============================== User Module Error Codes (1xxx) ===============================
     */

    /**
     * User not found
     */
    USER_NOT_FOUND(1001, "用户不存在"),

    /**
     * 密码错误
     * <p>
     * Password incorrect
     */
    USER_PASSWORD_ERROR(1002, "密码错误"),

    /**
     * User is disabled
     */
    USER_DISABLED(1003, "用户已禁用"),

    /**
     * Token 已过期
     * <p>
     * Token has expired
     */
    TOKEN_EXPIRED(1004, "Token 已过期"),

    /**
     * Token 无效
     * <p>
     * Token is invalid
     */
    TOKEN_INVALID(1005, "Token 无效"),

    /*
     *  =============================== 测试用例模块错误码 ===============================
     *  =============================== Test Case Module Error Codes (2xxx) ===============================
     */

    /**
     * Test case not found
     */
    CASE_NOT_FOUND(2001, "测试用例不存在"),

    /**
     * 用例版本冲突
     * <p>
     * Case version conflict
     */
    CASE_VERSION_CONFLICT(2002, "用例版本冲突"),

    /*
     * ===============================  测试任务模块错误码 ===============================
     * =============================== Test Task Module Error Codes (3xxx) ===============================
     */

    /**
     * Test task not found
     */
    TASK_NOT_FOUND(3001, "测试任务不存在"),

    /**
     * 任务已在执行中
     * <p>
     * Task is already running
     */
    TASK_ALREADY_RUNNING(3002, "任务已在执行中"),

    /*
     * =============================== 测试报告模块错误码 ===============================
     * =============================== Test Report Module Error Codes (4xxx) ===============================
     */

    /**
     * 测试报告不存在
     * <p>
     * Test report not found
     */
    REPORT_NOT_FOUND(4001, "测试报告不存在"),

    /*
     * ===============================  引擎与 AI 服务错误码 ===============================
     * =============================== Engine & AI Service Error Codes (5xxx) ===============================
     */

    /**
     * C 计算引擎不可用
     * <p>
     * C calculation engine unavailable
     */
    ENGINE_UNAVAILABLE(5001, "C 计算引擎不可用"),

    /**
     * AI 服务不可用
     * <p>
     * AI service unavailable
     */
    AI_SERVICE_UNAVAILABLE(5002, "AI 服务不可用"),

    /*
     * =============================== 代码分析模块错误码 ===============================
     * =============================== Code Analysis Module Error Codes (6xxx) ===============================
     */

    /**
     * 变更分析不存在
     * <p>
     * Change analysis not found
     */
    CHANGE_ANALYSIS_NOT_FOUND(6001, "变更分析不存在"),

    /**
     * 变更分析状态异常
     * <p>
     * Change analysis status error
     */
    CHANGE_ANALYSIS_STATUS_ERROR(6002, "变更分析状态异常"),

    /**
     * Git 仓库配置不存在
     * <p>
     * Git repository not found
     */
    GIT_REPOSITORY_NOT_FOUND(6003, "Git 仓库配置不存在"),

    /**
     * Git 仓库连接失败
     * <p>
     * Git repository connection failed
     */
    GIT_CONNECTION_FAILED(6004, "Git 仓库连接失败"),

    /**
     * Git 提交记录不存在
     * <p>
     * Git commit not found
     */
    GIT_COMMIT_NOT_FOUND(6005, "Git 提交记录不存在"),

    /**
     * 影响范围分析失败
     * <p>
     * Affected scope analysis failed
     */
    AFFECTED_SCOPE_ANALYSIS_FAILED(6006, "影响范围分析失败"),

    /**
     * 变更分析与提交关联不存在
     * <p>
     * Change analysis commit relation not found
     */
    ANALYSIS_COMMIT_RELATION_NOT_FOUND(6007, "变更分析与提交关联不存在");

    /**
     * Error code
     */
    @Schema(description = """
            错误码
            Error code
            """, example = "200")
    private final int code;

    /**
     * Error message
     */
    @Schema(description = """
            错误消息
            Error message
            """, example = "操作成功")
    private final String message;

}

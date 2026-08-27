package com.dwl.common.enums.agent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 工具调用状态枚举
 * Tool Call Status Enumeration
 * <p>
 * 用于表示 Agent 工具调用的执行状态。
 * <p>
 * Used to represent the execution status of an Agent tool call.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 23:25
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        工具调用状态
        Tool Call Status
        """)
public enum ToolCallStatus {

    /**
     * 成功 - 工具调用执行成功
     * Success - tool call executed successfully
     */
    SUCCESS("success", "成功"),

    /**
     * 失败 - 工具调用执行失败
     * Failed - tool call execution failed
     */
    FAILED("failed", "失败"),

    /**
     * 超时 - 工具调用超时
     * Timeout - tool call timed out
     */
    TIMEOUT("timeout", "超时");

    /**
     * 状态编码
     * Status code
     */
    @Schema(description = """
            状态编码
            Status code
            """, example = "success")
    private final String code;

    /**
     * 中文描述
     * Chinese description
     */
    @Schema(description = """
            中文描述
            Chinese description
            """, example = "成功")
    private final String description;

    /**
     * 根据编码获取枚举
     * Get enum by code
     *
     * @param code 状态编码 / Status code
     * @return ToolCallStatus enum, or null if not found
     */
    public static ToolCallStatus of(String code) {
        for (ToolCallStatus tcs : values()) {
            if (tcs.code.equals(code)) {
                return tcs;
            }
        }
        return null;
    }

    /**
     * 判断是否成功
     * Check if success
     *
     * @param code 状态编码 / Status code
     * @return true if success
     */
    public static boolean isSuccess(String code) {
        return code != null && SUCCESS.code.equals(code);
    }

    /**
     * 判断是否失败
     * Check if failed
     *
     * @param code 状态编码 / Status code
     * @return true if failed
     */
    public static boolean isFailed(String code) {
        return code != null && (FAILED.code.equals(code) || TIMEOUT.code.equals(code));
    }

}

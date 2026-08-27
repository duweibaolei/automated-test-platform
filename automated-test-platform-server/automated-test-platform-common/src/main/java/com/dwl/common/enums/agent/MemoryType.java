package com.dwl.common.enums.agent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 记忆类型枚举
 * Memory Type Enumeration
 * <p>
 * 用于表示 Agent 长期记忆的类型
 * <p>
 * Used to represent the type of Agent long-term memory.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 23:20
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        记忆类型
        Memory Type
        """)
public enum MemoryType {

    /**
     * 模式 - 存储分析模式与行为特征
     * Pattern - stores analysis patterns and behavioral characteristics
     */
    PATTERN("pattern", "模式"),

    /**
     * 偏好 - 存储用户偏好设置
     * Preference - stores user preference settings
     */
    PREFERENCE("preference", "偏好"),

    /**
     * 反馈 - 存储学习反馈数据
     * Feedback - stores learning feedback data
     */
    FEEDBACK("feedback", "反馈");

    /**
     * 类型编码
     * Type code
     */
    @Schema(description = """
            类型编码
            Type code
            """, example = "pattern")
    private final String code;

    /**
     * 中文描述
     * Chinese description
     */
    @Schema(description = """
            中文描述
            Chinese description
            """, example = "模式")
    private final String description;

    /**
     * 根据编码获取枚举
     * Get enum by code
     *
     * @param code 类型编码 / Type code
     * @return MemoryType enum, or null if not found
     */
    public static MemoryType of(String code) {
        for (MemoryType mt : values()) {
            if (mt.code.equals(code)) {
                return mt;
            }
        }
        return null;
    }

}

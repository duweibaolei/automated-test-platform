package com.dwl.common.enums;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 用例来源枚举
 * <p>
 * Source Type Enumeration
 * <p>
 * 定义测试用例的来源方式, 包括自动生成/手动录入和混合修改
 * <p>
 * Defines the source type of test case, including auto-generated
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-07 18:43
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        用例来源枚举
        Source Type Enumeration
        """)
public enum SourceType {

    AUTO("auto", "Webhook自动触发"),
    MANUAL("manual", "手动触发"),
    SCHEDULED("scheduled", "定时触发"),
    HYBRID("hybrid", "混合修改");

    @Schema(description = """
            来源编码
            Source type code
            """, example = "auto")
    private final String code;

    @Schema(description = """
            来源描述
            Source type description
            """, example = "自动生成")
    private final String description;


    /**
     * Get enum by code
     *
     * @param code Code
     * @return Enum value, or null if not found
     */
    public static SourceType fromCode(String code) {
        for (SourceType source : values()) {
            if (source.getCode().equals(code)) {
                return source;
            }
        }
        return null;
    }

    /**
     * Check if code exists
     *
     * @param code Code
     * @return Whether it exists
     */
    public static boolean exists(String code) {
        return Objects.nonNull(fromCode(code));
    }
}

package com.dwl.common.enums.quality;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 缺陷严重等级枚举
 * <p>
 * Defect Severity Enumeration
 * <p>
 * 用于表示缺陷的严重程度
 * <p>
 * Used to represent the severity level of a defect.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 23:40
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        缺陷严重等级
        Defect Severity
        """)
public enum DefectSeverity {

    /**
     * 致命 - 系统崩溃、数据丢失等严重影响
     * Critical - system crash, data loss and other serious impacts
     */
    CRITICAL("critical", "致命"),

    /**
     * 严重 - 主要功能失效, 有替代方案
     * Major - main function fails, has workaround
     */
    MAJOR("major", "严重"),

    /**
     * 一般 - 次要功能问题, 不影响主要功能
     * Minor - minor function issue, does not affect main function
     */
    MINOR("minor", "一般"),

    /**
     * 轻微 - 界面问题、建议等
     * Trivial - UI issues, suggestions, etc.
     */
    TRIVIAL("trivial", "轻微");

    /**
     * 等级编码
     * Severity code
     */
    @Schema(description = """
            等级编码
            Severity code
            """, example = "major")
    private final String code;

    /**
     * Chinese description
     */
    @Schema(description = """
            Chinese description
            """, example = "严重")
    private final String description;

    /**
     * Get enum by code
     *
     * @param code 等级编码
     *             Severity code
     * @return DefectSeverity enum, or null if not found
     */
    public static DefectSeverity of(String code) {
        for (DefectSeverity ds : values()) {
            if (ds.code.equals(code)) {
                return ds;
            }
        }
        return null;
    }

    /**
     * 判断是否为高优先级缺陷(致命或严重)
     * Check if it is a high priority defect (critical or major)
     *
     * @param code 等级编码
     *             Severity code
     * @return true if high priority
     */
    public static boolean isHighPriority(String code) {
        return CRITICAL.code.equals(code) || MAJOR.code.equals(code);
    }

}

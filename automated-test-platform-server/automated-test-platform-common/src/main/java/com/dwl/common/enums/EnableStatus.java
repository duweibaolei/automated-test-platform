package com.dwl.common.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * 启用状态枚举
 * <p>
 * Enable Status Enumeration
 * <p>
 * 用于表示实体是否启用/激活的状态。
 * <p>
 * 数据库存储为整数：1-启用，0-禁用。
 * <p>
 * Used to represent whether an entity is enabled or activated.
 * <p>
 * Stored as integer in database: 1-enabled, 0-disabled.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 23:00
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        启用状态
        Enable Status
        """)
public enum EnableStatus {

    /**
     * 启用 (值为 1)
     * <p>
     * Enabled (value is 1)
     */
    ENABLED(1, "启用"),

    /**
     * 禁用 (值为 0)
     * <p>
     * Disabled (value is 0)
     */
    DISABLED(0, "禁用");

    /**
     * Numeric code
     */
    @Schema(description = """
            数值编码
            Numeric code: 1-enabled, 0-disabled
            """, example = "1")
    private final int value;

    /**
     * Chinese description
     */
    @Schema(description = """
            中文描述
            Chinese description
            """, example = "启用")
    private final String description;

    /**
     * Get enum by numeric value
     *
     * @param value Numeric value
     * @return EnableStatus enum, or null if not found
     */
    public static EnableStatus of(int value) {
        for (EnableStatus es : values()) {
            if (es.value == value) {
                return es;
            }
        }
        return null;
    }

    /**
     * Check if enabled
     *
     * @param value Numeric value
     * @return true if enabled
     */
    public static boolean isEnabled(Integer value) {
        return Optional.ofNullable(value).map(v -> v == ENABLED.value).orElse(false);
    }

    /**
     * Check if disabled
     *
     * @param value Numeric value
     * @return true if disabled
     */
    public static boolean isDisabled(Integer value) {
        return Optional.ofNullable(value).map(v -> v == DISABLED.value).orElse(false);
    }

}

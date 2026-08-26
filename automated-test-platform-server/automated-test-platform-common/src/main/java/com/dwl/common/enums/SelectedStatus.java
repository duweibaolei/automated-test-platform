package com.dwl.common.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * 选入状态枚举
 * <p>
 * Selected Status Enumeration
 * <p>
 * 用于表示某项是否被选入特定范围(如回归测试范围)
 * <p>
 * 数据库存储为整数: 1-已选入, 0-未选入
 * <p>
 * Used to represent whether an item is selected into a specific scope
 * (e.g., regression test scope)
 * <p>
 * Stored as integer in database: 1-selected, 0-not selected.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 23:10
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        选入状态
        Selected Status
        """)
public enum SelectedStatus {

    /**
     * 已选入 (值为 1)
     * <p>
     * Selected (value is 1)
     */
    SELECTED(1, "已选入"),

    /**
     * 未选入 (值为 0)
     * <p>
     * Not selected (value is 0)
     */
    NOT_SELECTED(0, "未选入");

    /**
     * Numeric code
     */
    @Schema(description = """
            数值编码
            Numeric code: 1-selected, 0-not selected
            """, example = "1")
    private final int value;

    /**
     * Chinese description
     */
    @Schema(description = """
            中文描述
            Chinese description
            """, example = "已选入")
    private final String description;

    /**
     * Get enum by numeric value
     *
     * @param value Numeric value
     * @return SelectedStatus enum, or null if not found
     */
    public static SelectedStatus of(int value) {
        for (SelectedStatus ss : values()) {
            if (ss.value == value) {
                return ss;
            }
        }
        return null;
    }

    /**
     * Check if selected
     *
     * @param value Numeric value
     * @return true if selected
     */
    public static boolean isSelected(Integer value) {
        return Optional.ofNullable(value).map(v -> v == SELECTED.value).orElse(false);
    }

    /**
     * Check if not selected
     *
     * @param value Numeric value
     * @return true if not selected
     */
    public static boolean isNotSelected(Integer value) {
        return Optional.ofNullable(value).map(v -> v == NOT_SELECTED.value).orElse(false);
    }

}

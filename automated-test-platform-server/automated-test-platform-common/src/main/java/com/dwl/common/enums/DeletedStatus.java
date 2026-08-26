package com.dwl.common.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * 删除状态枚举(逻辑删除)
 * <p>
 * Deleted Status Enumeration (Logical Delete)
 * <p>
 * 用于表示实体是否已被逻辑删除, 数据库存储为整数: 0-未删除, 1-已删除
 * <p>
 * Used to represent whether an entity has been logically deleted
 * <p>
 * Stored as integer in database: 0-not deleted, 1-deleted
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 23:05
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        Deleted Status
        """)
public enum DeletedStatus {

    /**
     * Not deleted (value is 0)
     */
    NOT_DELETED(0, "未删除"),

    /**
     * 已删除 (值为 1)
     * Deleted (value is 1)
     */
    DELETED(1, "已删除");

    /**
     * Numeric code
     */
    @Schema(description = """
            数值编码
            Numeric code: 0-not deleted, 1-deleted
            """, example = "0")
    private final int value;

    /**
     * Chinese description
     */
    @Schema(description = """
            中文描述
            Chinese description
            """, example = "未删除")
    private final String description;

    /**
     * 根据数值获取枚举
     * <p>
     * Get enum by numeric value
     *
     * @param value 数值
     *              Numeric value
     * @return DeletedStatus enum, or null if not found
     */
    public static DeletedStatus of(int value) {
        for (DeletedStatus ds : values()) {
            if (ds.value == value) {
                return ds;
            }
        }
        return null;
    }

    /**
     *
     * Check if not deleted
     *
     * @param value Numeric value
     * @return true if not deleted
     */
    public static boolean isNotDeleted(Integer value) {
        return Optional.ofNullable(value).map(v -> v == NOT_DELETED.value).orElse(false);
    }

    /**
     * Check if deleted
     *
     * @param value Numeric value
     * @return true if deleted
     */
    public static boolean isDeleted(Integer value) {
        return Optional.ofNullable(value).map(v -> v == DELETED.value).orElse(false);
    }

}

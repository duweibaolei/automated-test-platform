package com.dwl.common.enums.testmanagement;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 受影响类型枚举
 * Affected type enumeration
 * <p>
 * 定义代码变更对测试用例的影响类型：
 * - added: 新增影响，对应新增的代码功能
 * - modified: 修改影响，对应修改的代码功能
 * - deleted: 删除影响，对应删除的代码功能
 * - risk_affected: 风险影响，可能受影响的关联功能
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-28 18:30
 */
@Getter
@AllArgsConstructor
public enum AffectedType {

    /**
     * 新增影响
     * Added impact - corresponds to newly added code functionality
     */
    ADDED("added", "新增影响，对应新增的代码功能"),

    /**
     * 修改影响
     * Modified impact - corresponds to modified code functionality
     */
    MODIFIED("modified", "修改影响，对应修改的代码功能"),

    /**
     * 删除影响
     * Deleted impact - corresponds to deleted code functionality
     */
    DELETED("deleted", "删除影响，对应删除的代码功能"),

    /**
     * 风险影响
     * Risk affected - potentially affected associated functionality
     */
    RISK_AFFECTED("risk_affected", "风险影响，可能受影响的关联功能");

    /**
     * 编码
     * Code
     */
    private final String code;

    /**
     * 描述
     * Description
     */
    private final String description;

    /**
     * 根据编码获取枚举
     * Get enum by code
     *
     * @param code 编码 Code
     * @return 枚举值 Enum value, or null if not found
     */
    public static AffectedType fromCode(String code) {
        for (AffectedType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    /**
     * 判断是否存在该编码
     * Check if code exists
     *
     * @param code 编码 Code
     * @return 是否存在 Whether it exists
     */
    public static boolean exists(String code) {
        return Objects.nonNull(fromCode(code));
    }
}

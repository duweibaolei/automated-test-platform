package com.dwl.common.enums.testmanagement;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 处理状态枚举
 * Handling status enumeration
 * <p>
 * 定义受代码变更影响的测试用例的处理状态：
 * - pending: 待处理，需要人工确认如何处理
 * - tested: 已测试，已完成相关测试验证
 * - bypassed: 已绕过，经评估无需测试或影响可接受
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-28 18:35
 */
@Getter
@AllArgsConstructor
public enum HandlingStatus {

    /**
     * 待处理
     * Pending - needs manual confirmation on how to handle
     */
    PENDING("pending", "待处理，需要人工确认如何处理"),

    /**
     * 已测试
     * Tested - related test verification completed
     */
    TESTED("tested", "已测试，已完成相关测试验证"),

    /**
     * 已绕过
     * Bypassed - assessed as not needing testing or impact is acceptable
     */
    BYPASSED("bypassed", "已绕过，经评估无需测试或影响可接受");

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
    public static HandlingStatus fromCode(String code) {
        for (HandlingStatus status : values()) {
            if (status.getCode().equals(code)) {
                return status;
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

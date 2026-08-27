package com.dwl.common.enums.testmanagement;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 断言类型枚举
 * <p>
 * Assertion type enumeration
 * <p>
 * 定义测试用例步骤的几种主流断言方式：
 * - url_contains: URL 包含检查
 * - url_match: URL 完全匹配
 * - visible: 元素可见性检查
 * - hidden: 元素隐藏检查
 * - text_match: 文本完全匹配
 * - text_contains: 文本包含检查
 * - value_match: 值完全匹配
 * - value_contains: 值包含检查
 * - attribute: 属性检查
 * - count: 元素数量检查
 * - enabled: 元素启用状态检查
 * - disabled: 元素禁用状态检查
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-28 18:00
 */
@Getter
@AllArgsConstructor
public enum AssertType {

    /**
     * URL 包含检查
     * URL contains check
     */
    URL_CONTAINS("url_contains", "URL 包含检查"),

    /**
     * URL 完全匹配
     * URL exact match
     */
    URL_MATCH("url_match", "URL 完全匹配"),

    /**
     * 元素可见性检查
     * Element visibility check
     */
    VISIBLE("visible", "元素可见性检查"),

    /**
     * 元素隐藏检查
     * Element hidden check
     */
    HIDDEN("hidden", "元素隐藏检查"),

    /**
     * 文本完全匹配
     * Text exact match
     */
    TEXT_MATCH("text_match", "文本完全匹配"),

    /**
     * 文本包含检查
     * Text contains check
     */
    TEXT_CONTAINS("text_contains", "文本包含检查"),

    /**
     * 值完全匹配
     * Value exact match
     */
    VALUE_MATCH("value_match", "值完全匹配"),

    /**
     * 值包含检查
     * Value contains check
     */
    VALUE_CONTAINS("value_contains", "值包含检查"),

    /**
     * 属性检查
     * Attribute check
     */
    ATTRIBUTE("attribute", "属性检查"),

    /**
     * 元素数量检查
     * Element count check
     */
    COUNT("count", "元素数量检查");

    /**
     * Code
     */
    private final String code;

    /**
     * Description
     */
    private final String description;

    /**
     * Get enum by code
     *
     * @param code Code
     * @return Enum value, or null if not found
     */
    public static AssertType fromCode(String code) {
        for (AssertType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
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
        return fromCode(code) != null;
    }
}

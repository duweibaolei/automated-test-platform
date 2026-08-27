package com.dwl.common.enums.testmanagement;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 元素定位类型枚举
 *
 * Element locator type enumeration
 * <p>
 * 定义页面元素的几种主流定位方式：
 * - css: CSS 选择器
 * - xpath: XPath 表达式
 * - id: ID 属性
 * - data-testid: data-testid 属性
 * - name: Name 属性
 * - className: Class Name 属性
 * - tagName: Tag Name 属性
 * - linkText: 链接文本
 * - partialLinkText: 部分链接文本
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-28 17:30
 */
@Getter
@AllArgsConstructor
public enum LocatorType {

    /**
     * CSS selector
     */
    CSS("css", "CSS 选择器"),

    /**
     * XPath expression
     */
    XPATH("xpath", "XPath 表达式"),

    /**
     * ID 属性
     * ID attribute
     */
    ID("id", "ID 属性"),

    /**
     * data-testid 属性
     * data-testid attribute
     */
    DATA_TEST_ID("data-testid", "data-testid 属性"),

    /**
     * Name attribute
     */
    NAME("name", "Name 属性"),

    /**
     * Class Name attribute
     */
    CLASS_NAME("className", "Class Name 属性"),

    /**
     * Tag Name attribute
     */
    TAG_NAME("tagName", "Tag Name 属性"),

    /**
     * Link text
     */
    LINK_TEXT("linkText", "链接文本"),

    /**
     * 部分链接文本
     * Partial link text
     */
    PARTIAL_LINK_TEXT("partialLinkText", "部分链接文本");

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
     * @param code  Code
     * @return  Enum value, or null if not found
     */
    public static LocatorType fromCode(String code) {
        for (LocatorType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    /**
     * Check if code exists
     *
     * @param code  Code
     * @return  Whether it exists
     */
    public static boolean exists(String code) {
        return Objects.nonNull(fromCode(code));
    }
}

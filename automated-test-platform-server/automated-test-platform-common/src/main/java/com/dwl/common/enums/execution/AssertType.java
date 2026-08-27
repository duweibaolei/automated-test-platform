package com.dwl.common.enums.execution;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 断言类型枚举
 * <p>
 * Assert Type Enumeration
 * <p>
 * 定义 Web 自动化测试中可执行的断言类型
 * <p>
 * Defines the assertion types executable in web automation testing
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-27 10:00
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        断言类型枚举
        Assert Type Enumeration
        """)
public enum AssertType {

    /* ==================== 元素存在性断言 ====================
     * ==================== Element existence assertions ==================== */

    /**
     * 元素存在
     * Element exists
     */
    EXISTS("exists", "元素存在", "Element exists"),

    /**
     * 元素不存在
     * Element does not exist
     */
    NOT_EXISTS("not_exists", "元素不存在", "Element does not exist"),

    /* ==================== 元素可见性断言 ====================
     * ==================== Element visibility assertions ==================== */

    /**
     * 元素可见
     * Element is visible
     */
    VISIBLE("visible", "元素可见", "Element is visible"),

    /**
     * 元素隐藏
     * Element is hidden
     */
    HIDDEN("hidden", "元素隐藏", "Element is hidden"),

    /**
     * 元素显示
     * Element is displayed
     */
    DISPLAYED("displayed", "元素显示", "Element is displayed"),

    /* ==================== 元素状态断言 ====================
     * ==================== Element state assertions ==================== */


    /**
     * 元素启用
     * Element is enabled
     */
    ENABLED("enabled", "元素启用", "Element is enabled"),

    /**
     * 元素禁用
     * Element is disabled
     */
    DISABLED("disabled", "元素禁用", "Element is disabled"),

    /**
     * 元素选中
     * Element is selected
     */
    SELECTED("selected", "元素选中", "Element is selected"),


    /* ==================== 文本内容断言 ====================
     * ==================== Text content assertions ==================== */


    /**
     * 文本等于
     * Text equals exact value
     */
    TEXT_EQUALS("text_equals", "文本等于", "Text equals exact value"),

    /**
     * 文本包含
     * Text contains substring
     */
    TEXT_CONTAINS("text_contains", "文本包含", "Text contains substring"),

    /**
     * 文本以...开头
     * Text starts with
     */
    TEXT_STARTS_WITH("text_starts_with", "文本以...开头", "Text starts with"),

    /**
     * 文本以...结尾
     * Text ends with
     */
    TEXT_ENDS_WITH("text_ends_with", "文本以...结尾", "Text ends with"),

    /**
     * 文本完全匹配
     * Text exact match
     */
    TEXT_MATCH("text_match", "文本完全匹配", "Text exact match"),

    /* ==================== 属性断言 ====================
     * ==================== Attribute assertions ==================== */

    /**
     * 拥有属性
     * Element has attribute
     */
    HAS_ATTRIBUTE("has_attribute", "拥有属性", "Element has attribute"),

    /**
     * 属性等于
     * Attribute equals value
     */
    ATTRIBUTE_EQUALS("attribute_equals", "属性等于", "Attribute equals value"),

    /**
     * 拥有类名
     * Element has class
     */
    HAS_CLASS("has_class", "拥有类名", "Element has class"),

    /**
     * 属性检查
     * Attribute check
     */
    ATTRIBUTE("attribute", "属性检查", "Attribute check"),

    /* ==================== 值断言 ====================
     * ==================== Value assertions ==================== */

    /**
     * 值等于
     * Input value equals
     */
    VALUE_EQUALS("value_equals", "值等于", "Input value equals"),

    /**
     * 值包含
     * Input value contains
     */
    VALUE_CONTAINS("value_contains", "值包含", "Input value contains"),

    /**
     * 值完全匹配
     * Value exact match
     */
    VALUE_MATCH("value_match", "值完全匹配", "Value exact match"),

    /* ==================== CSS/样式断言 ====================
     * ==================== CSS/Style assertions ==================== */

    /**
     * 拥有 CSS 属性
     * Has CSS property
     */
    HAS_CSS_PROPERTY("has_css_property", "拥有 CSS 属性", "Has CSS property"),

    /* ==================== URL 断言 ====================
     * ==================== CSS/Style assertions ==================== */

    /**
     * URL 等于
     * URL equals
     */
    URL_EQUALS("url_equals", "URL 等于", "URL equals"),

    /**
     * URL 包含
     * URL contains
     */
    URL_CONTAINS("url_contains", "URL 包含", "URL contains"),

    /**
     * URL 完全匹配
     * URL exact match
     */
    URL_MATCH("url_match", "URL 完全匹配", "URL exact match"),

    /* ==================== 标题断言 ====================
     * ==================== Title assertions ==================== */


    /**
     * 标题等于
     * Page title equals
     */
    TITLE_EQUALS("title_equals", "标题等于", "Page title equals"),

    /**
     * 标题包含
     * Page title contains
     */
    TITLE_CONTAINS("title_contains", "标题包含", "Page title contains"),

    /* ==================== 数量断言 ====================
     * ==================== Count assertions ==================== */


    /**
     * 元素数量等于
     * Element count equals
     */
    ELEMENT_COUNT_EQUALS("element_count_equals", "元素数量等于", "Element count equals"),

    /**
     * 元素数量大于
     * Element count greater than
     */
    ELEMENT_COUNT_GREATER_THAN("element_count_greater_than", "元素数量大于", "Element count greater than"),

    /**
     * 元素数量检查
     * Element count check
     */
    COUNT("count", "元素数量检查", "Element count check"),

    /* ==================== Cookie 断言 ====================
     * ==================== Cookie assertions ==================== */


    /**
     * Cookie 存在
     * Cookie exists
     */
    COOKIE_EXISTS("cookie_exists", "Cookie 存在", "Cookie exists"),

    /**
     * Cookie 等于
     * Cookie equals
     */
    COOKIE_EQUALS("cookie_equals", "Cookie 等于", "Cookie equals");


    /**
     * 断言类型编码
     * Assert type code
     */
    @Schema(description = """
            断言类型编码
            Assert type code
            """, example = "visible")
    private final String code;

    /**
     * 断言类型中文描述
     * Assert type Chinese description
     */
    @Schema(description = """
            断言类型中文描述
            Assert type Chinese description
            """, example = "元素可见")
    private final String description;

    /**
     * 断言类型英文描述
     * Assert type English description
     */
    @Schema(description = """
            断言类型英文描述
            Assert type English description
            """, example = "Element is visible")
    private final String englishDescription;

    /**
     * Get enum by code
     *
     * @param code Status code
     * @return AssertType enum, or null if not found
     */
    public static AssertType fromCode(String code) {
        for (AssertType at : values()) {
            if (at.code.equals(code)) {
                return at;
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

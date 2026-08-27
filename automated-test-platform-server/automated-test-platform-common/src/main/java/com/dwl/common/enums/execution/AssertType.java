package com.dwl.common.enums.execution;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

    // 元素存在性断言
    EXISTS("exists", "元素存在", "Element exists"),
    NOT_EXISTS("notExists", "元素不存在", "Element does not exist"),

    // 元素可见性断言
    VISIBLE("visible", "元素可见", "Element is visible"),
    HIDDEN("hidden", "元素隐藏", "Element is hidden"),
    DISPLAYED("displayed", "元素显示", "Element is displayed"),

    // 元素状态断言
    ENABLED("enabled", "元素启用", "Element is enabled"),
    DISABLED("disabled", "元素禁用", "Element is disabled"),
    SELECTED("selected", "元素选中", "Element is selected"),

    // 文本内容断言
    TEXT_CONTAINS("textContains", "文本包含", "Text contains substring"),
    TEXT_EQUALS("textEquals", "文本等于", "Text equals exact value"),
    TEXT_STARTS_WITH("textStartsWith", "文本以...开头", "Text starts with"),
    TEXT_ENDS_WITH("textEndsWith", "文本以...结尾", "Text ends with"),

    // 属性断言
    HAS_ATTRIBUTE("hasAttribute", "拥有属性", "Element has attribute"),
    ATTRIBUTE_EQUALS("attributeEquals", "属性等于", "Attribute equals value"),
    HAS_CLASS("hasClass", "拥有类名", "Element has class"),

    // 值断言
    VALUE_EQUALS("valueEquals", "值等于", "Input value equals"),
    VALUE_CONTAINS("valueContains", "值包含", "Input value contains"),

    // CSS/样式断言
    HAS_CSS_PROPERTY("hasCssProperty", "拥有 CSS 属性", "Has CSS property"),

    // URL 断言
    URL_EQUALS("urlEquals", "URL 等于", "URL equals"),
    URL_CONTAINS("urlContains", "URL 包含", "URL contains"),

    // 标题断言
    TITLE_EQUALS("titleEquals", "标题等于", "Page title equals"),
    TITLE_CONTAINS("titleContains", "标题包含", "Page title contains"),

    // 数量断言
    ELEMENT_COUNT_EQUALS("elementCountEquals", "元素数量等于", "Element count equals"),
    ELEMENT_COUNT_GREATER_THAN("elementCountGreaterThan", "元素数量大于", "Element count greater than"),

    // Cookie 断言
    COOKIE_EXISTS("cookieExists", "Cookie 存在", "Cookie exists"),
    COOKIE_EQUALS("cookieEquals", "Cookie 等于", "Cookie equals");


    @Schema(description = """
            断言类型编码
            Assert type code
            """, example = "visible")
    private final String code;

    @Schema(description = """
            断言类型中文描述
            Assert type Chinese description
            """, example = "元素可见")
    private final String description;

    @Schema(description = """
            断言类型英文描述
            Assert type English description
            """, example = "Element is visible")
    private final String englishDescription;
}

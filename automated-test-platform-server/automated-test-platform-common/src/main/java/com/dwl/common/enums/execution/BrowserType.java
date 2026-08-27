package com.dwl.common.enums.execution;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 浏览器类型枚举
 * <p>
 * Browser Type Enumeration
 * <p>
 * 定义 Web 自动化测试支持的浏览器类型
 * Defines the browser types supported for web automation testing
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-27 10:00
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        浏览器类型枚举
        Browser Type Enumeration
        """)
public enum BrowserType {

    /**
     * Chromium 内核浏览器(Chrome、Edge 等)
     * Chromium-based browsers (Chrome, Edge, etc.)
     */
    CHROMIUM("chromium", "Chromium", "Chromium-based browser"),

    /**
     * Firefox 浏览器
     * Mozilla Firefox browser
     */
    FIREFOX("firefox", "Firefox", "Mozilla Firefox browser"),

    /**
     * WebKit 浏览器(Safari)
     * WebKit browser (Safari)
     */
    WEBKIT("webkit", "WebKit", "WebKit-based browser (Safari)");


    @Schema(description = """
            浏览器类型编码
            Browser type code
            """, example = "chromium")
    private final String code;

    @Schema(description = """
            浏览器类型中文描述
            Browser type Chinese description
            """, example = "Chromium")
    private final String description;

    @Schema(description = """
            浏览器类型英文描述
            Browser type English description
            """, example = "Chromium-based browser")
    private final String englishDescription;
}

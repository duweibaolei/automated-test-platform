package com.dwl.common.enums.execution;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 断言结果枚举
 * <p>
 * Assert Result Enumeration
 * <p>
 * 定义断言执行的结果状态
 * <p>
 * Defines the result status of assertion execution
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-27 10:00
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        断言结果枚举
        Assert Result Enumeration
        """)
public enum AssertResult {

    /**
     * 断言通过
     * Assertion passed
     */
    PASSED(1, "通过", "Passed"),

    /**
     * 断言失败
     * Assertion failed
     */
    FAILED(0, "失败", "Failed"),

    /**
     * 未执行断言(步骤不含断言)
     * No assertion executed (step has no assertion)
     */
    NOT_APPLICABLE(-1, "不适用", "Not applicable");


    @Schema(description = """
            断言结果值
            Assert result value
            """, example = "1")
    private final Integer value;

    @Schema(description = """
            断言结果中文描述
            Assert result Chinese description
            """, example = "通过")
    private final String description;

    @Schema(description = """
            断言结果英文描述
            Assert result English description
            """, example = "Passed")
    private final String englishDescription;
}

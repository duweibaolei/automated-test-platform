package com.dwl.common.enums;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Execution Status Enumeration
 * <p>
 * 定义测试用例执行结果的详细状态
 * Defines the detailed status of test case execution results
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-06 01:28
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        执行状态枚举
        Execution Status Enumeration
        """)
public enum ExecutionStatus {

    PENDING("pending", "待执行"),
    RUNNING("running", "执行中"),
    PASSED("passed", "通过"),
    FAILED("failed", "失败"),
    SKIPPED("skipped", "跳过"),
    ERROR("error", "错误");

    /**
     * 状态编码
     * Status code
     */
    @Schema(description = """
            Status code
            """, example = "passed")
    private final String code;

    /**
     * 状态描述
     * Status description
     */
    @Schema(description = """
            Status description
            """, example = "通过")
    private final String description;

}

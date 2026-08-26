package com.dwl.common.enums.test_management;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用例状态枚举
 * <p>
 * Case Status Enumeration
 * <p>
 * 定义测试用例的当前状态, 用于标识用例在生命周期的阶段
 * <p>
 * Defines the current status of a test case, indicating its phase in the lifecycle
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-06 01:10
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        用例状态枚举
        Case Status Enumeration
        """)
public enum CaseStatus {

    /**
     * 活跃(正常可用)
     * <p>
     * Active (normally usable)
     */
    ACTIVE("active", "活跃"), 

    /**
     * 不稳定(偶尔失败)
     * <p>
     * Unstable (occasionally fails)
     */
    UNSTABLE("unstable", "不稳定"), 

    /**
     * 已禁用
     * <p>
     * Disabled
     */
    DISABLED("disabled", "已禁用"), 

    /**
     * 草稿
     * <p>
     * Draft
     */
    DRAFT("draft", "草稿");

    @Schema(description = """
            状态编码
            Status code
            """, example = "active")
    private final String code;

    @Schema(description = """
            状态描述
            Status description
            """, example = "活跃")
    private final String description;

}

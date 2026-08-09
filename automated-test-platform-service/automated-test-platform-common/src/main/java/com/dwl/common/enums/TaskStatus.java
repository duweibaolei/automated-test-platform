package com.dwl.common.enums;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务状态编码
 * Task Status Enumeration
 * <p>
 * 定义测试任务的生命周期状态
 * Defines the lifecycle states of a test task
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-10 01:22
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        任务状态枚举
        Task Status Enumeration
        """)
public enum TaskStatus {

    PENDING("pending", "待执行"),
    RUNNING("running", "执行中"),
    PAUSED("paused", "已暂停"),
    COMPLETED("completed", "已完成"),
    FAILED("failed", "执行失败"),
    CANCELLED("cancelled", "已取消");

    @Schema(description = """
            状态编码
            Status code
            """, example = "pending")
    private final String code;

    @Schema(description = """
            状态描述
            Status description
            """, example = "待执行")
    private final String description;
}

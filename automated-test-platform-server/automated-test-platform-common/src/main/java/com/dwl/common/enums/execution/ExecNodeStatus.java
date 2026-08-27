package com.dwl.common.enums.execution;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 执行节点状态枚举
 * <p>
 * Execution Node Status Enumeration
 * <p>
 * 定义执行节点的运行状态, 包括健康、离线、忙碌等状态
 * <p>
 * Defines the runtime status of execution nodes, including healthy, offline, busy, etc.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-26 22:30
 */
@Getter
@AllArgsConstructor
@Schema(description = """
        执行节点状态
        Execution Node Status
        """)
public enum ExecNodeStatus {

    /**
     * 健康 - 节点正常可用
     * <p>
     * Healthy - node is normal and available
     */
    HEALTHY("healthy", "健康"), 

    /**
     * 离线 - 节点超时未心跳
     * <p>
     * Offline - node timeout without heartbeat
     */
    OFFLINE("offline", "离线"), 

    /**
     * 忙碌 - 节点正在执行任务
     * <p>
     * Busy - node is executing tasks
     */
    BUSY("busy", "忙碌");

    /**
     * Status code
     */
    @Schema(description = """
            Status code
            """, example = "healthy")
    private final String code;

    /**
     * Status description
     */
    @Schema(description = """
            Status description
            """, example = "健康")
    private final String description;

}

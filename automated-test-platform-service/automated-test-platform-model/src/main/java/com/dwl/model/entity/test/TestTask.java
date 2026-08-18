package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 测试任务实体
 * Test Task Entity
 * 对应表 test_task，管理测试任务的调度、执行进度和结果统计。
 * Maps to table test_task, managing test task scheduling, execution progress, and result statistics.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 17:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("test_task")
@Schema(description = """
        测试任务
        Test Task
        """)
public class TestTask extends BaseEntity {

    @Schema(description = """
            任务编号
            Task number: TK-NNNN
            """, example = "TK-0001")
    private String taskNo;

    @Schema(description = """
            任务名称
            Task name
            """, example = "登录模块回归测试")
    private String taskName;

    @Schema(description = """
            触发来源
            Trigger source: auto/manual/scheduled
            """, example = "auto")
    private String triggerSource;

    @Schema(description = """
            手动触发用户 ID
            Manual trigger user ID
            """)
    private Long triggerUserId;

    @Schema(description = """
            关联变更分析 ID
            Associated change analysis ID (for auto-triggered tasks)
            """)
    private Long analysisId;

    @Schema(description = """
            执行环境 ID
            Execution environment ID
            """)
    private Long envId;

    @Schema(description = """
            浏览器类型
            Browser type
            """, example = "chromium")
    private String browserType;

    @Schema(description = """
            并发数
            Concurrency level
            """, example = "1")
    private Integer concurrency;

    @Schema(description = """
            失败重试次数
            Failure retry count
            """, example = "0")
    private Integer retryCount;

    @Schema(description = """
            定时 CRON 表达式
            Schedule CRON expression (for scheduled tasks)
            """)
    private String scheduleCron;

    @Schema(description = """
            状态
            Status: pending/running/paused/completed/failed/cancelled
            """, example = "pending")
    private String status;

    @Schema(description = """
            执行进度
            Execution progress (%)
            """, example = "0")
    private Integer progress;

    @Schema(description = """
            通过数
            Pass count
            """, example = "0")
    private Integer passCount;

    @Schema(description = """
            失败数
            Fail count
            """, example = "0")
    private Integer failCount;

    @Schema(description = """
            跳过数
            Skip count
            """, example = "0")
    private Integer skipCount;

    @Schema(description = """
            总用例数
            Total case count
            """, example = "0")
    private Integer totalCount;

    @Schema(description = """
            开始时间
            Start time
            """)
    private LocalDateTime startTime;

    @Schema(description = """
            结束时间
            End time
            """)
    private LocalDateTime endTime;

    @Schema(description = """
            执行耗时
            Execution duration (milliseconds)
            """)
    private Long durationMs;

}

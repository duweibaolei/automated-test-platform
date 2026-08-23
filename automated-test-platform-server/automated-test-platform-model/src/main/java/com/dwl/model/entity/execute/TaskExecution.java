package com.dwl.model.entity.execute;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 任务执行记录实体
 * Task Execution Record Entity
 * 对应表 task_execution,记录单条用例在任务中的执行实例
 * Maps to table task_execution, recording the execution instance of a single case within a task
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 14:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("task_execution")
@Schema(description = """
        任委执行记录
        Task Execution Record
        """)
public class TaskExecution extends BaseEntity {

    @Schema(description = """
            任务 ID
            Task ID
            """, example = "1")
    private Long taskId;

    @Schema(description = """
            用例 ID
            Case ID
            """, example = "1")
    private Long caseId;

    @Schema(description = """
            执行节点 ID
            Execution node ID
            """)
    private Long nodeId;

    @Schema(description = """
            重试序号(0 为首次)
            Retry index(0 for first attempt)
            """, example = "0")
    private Integer retryIndex;

    @Schema(description = """
            Status: pending running passed failed skipped error
            """, example = "pending")
    private String status;

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
            执行耗时(毫秒)
            Execution duration(ms)
            """)
    private Long durationMs;

    @Schema(description = """
            失败步骤序号
            Failed step order number
            """)
    private Integer failedStep;

    @Schema(description = """
            错误信息
            Error message
            """)
    private String errorMessage;

    @Schema(description = """
            失败截图 URL
            Failure screenshot URL
            """)
    private String screenshotUrl;

    @Schema(description = """
            执行录像 URL
            Execution video URL
            """)
    private String videoUrl;

}

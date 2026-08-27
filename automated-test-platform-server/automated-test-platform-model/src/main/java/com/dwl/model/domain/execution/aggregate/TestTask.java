package com.dwl.model.domain.execution.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.SourceType;
import com.dwl.common.enums.execution.BrowserType;
import com.dwl.common.enums.execution.ExecutionTaskStatus;
import com.dwl.model.domain.execution.entity.TaskCaseRelation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 测试任务聚合根
 * Test Task Aggregate Root
 * <p>
 * 执行管控域的核心聚合根, 封装测试任务的业务规则
 * 业务规则: 任务状态机 {@link ExecutionTaskStatus }、并发控制、幂等触发
 * <p>
 * Core aggregate root of the Execution Control domain, encapsulating test task business rules.
 * Business rules: task state machine (@link TaskStatus },
 * concurrency control, idempotent trigger.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("test_task")
@Schema(description = """
        测试任务聚合根
        Test Task Aggregate Root
        """)
public class TestTask extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            Task ID
            """)
    private Long id;

    @Schema(description = """
            Task number
            """, example = "TK-0001")
    private String taskNo;

    @Schema(description = """
            Task name
            """, example = "登录模块回归测试")
    private String taskName;

    @Schema(description = """
            触发来源
            Trigger source
            """, example = "auto",
            implementation = SourceType.class)
    private String triggerSource;

    @Schema(description = """
            手动触发用户 ID
            Manual trigger user ID
            """)
    private Long triggerUserId;

    @Schema(description = """
            关联变更分析 ID
            Associated change analysis ID
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
            """, example = "chromium",
            implementation = BrowserType.class)
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
            Schedule CRON expression
            """)
    private String scheduleCron;

    @Schema(description = """
            Status
            """,
            example = "pending"
            , implementation = ExecutionTaskStatus.class)
    private String status;

    @Schema(description = """
            执行进度
            Execution progress (%)
            """, example = "0")
    private Integer progress;

    @Schema(description = """
            Pass count
            """, example = "0")
    private Integer passCount;

    @Schema(description = """
            Fail count
            """, example = "0")
    private Integer failCount;

    @Schema(description = """
            Skip count
            """, example = "0")
    private Integer skipCount;

    @Schema(description = """
            Total case count
            """, example = "0")
    private Integer totalCount;

    @Schema(description = """
            Start time
            """)
    private LocalDateTime startTime;

    @Schema(description = """
            End time
            """)
    private LocalDateTime endTime;

    @Schema(description = """
            执行耗时
            Execution duration (milliseconds)
            """)
    private Long durationMs;

    @Schema(description = """
            Logical delete flag
            """, example = "0",
            implementation = DeletedStatus.class)
    private Integer isDelete;

    @Schema(description = """
            Create time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            Update time
            """)
    private LocalDateTime updatedAt;

    @Builder.Default
    private transient List<TaskCaseRelation> caseRelations = new ArrayList<>();

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建测试任务
     * Factory Method: Create test task
     *
     * @param taskNo        Task number
     * @param taskName      Task name
     * @param triggerSource 触发来源
     *                      Trigger source
     * @param triggerUserId 手动触发用户 ID
     *                      Manual trigger user ID
     * @param analysisId    关联变更分析 ID
     *                      Associated change analysis ID
     * @param envId         执行环境 ID
     *                      Execution environment ID
     * @param browserType   浏览器类型
     *                      Browser type
     * @param concurrency   并发数
     *                      Concurrency level
     * @param retryCount    失败重试次数
     *                      Failure retry count
     * @param scheduleCron  定时 CRON 表达式
     *                      Schedule CRON expression
     * @return New test task
     */
    public static TestTask create(String taskNo, String taskName, String triggerSource, Long triggerUserId,
                                  Long analysisId, Long envId, String browserType, Integer concurrency,
                                  Integer retryCount, String scheduleCron) {
        return TestTask.builder()
                .taskNo(taskNo).taskName(taskName).triggerSource(triggerSource).triggerUserId(triggerUserId)
                .analysisId(analysisId).envId(envId).browserType(browserType).concurrency(concurrency)
                .retryCount(retryCount).scheduleCron(scheduleCron).status(ExecutionTaskStatus.PENDING.getCode()).progress(0)
                .passCount(0).failCount(0).skipCount(0).totalCount(0).caseRelations(new ArrayList<>())
                .build();
    }

    /**
     * 添加用例
     * Add case
     *
     * @param caseId Case ID
     */
    public void addCase(Long caseId) {
        this.caseRelations.add(TaskCaseRelation.create(this.id, caseId));
        this.totalCount++;
    }

    /**
     * 开始任务
     * Start task
     */
    public void start() {
        this.status = ExecutionTaskStatus.RUNNING.getCode();
        this.startTime = LocalDateTime.now();
    }

    /**
     * 暂停任务
     * Pause task
     */
    public void pause() {
        this.status = ExecutionTaskStatus.PAUSED.getCode();
    }

    /**
     * 恢复任务
     * Resume task
     */
    public void resume() {
        this.status = ExecutionTaskStatus.RUNNING.getCode();
    }

    /**
     * 取消任务
     * Cancel task
     */
    public void cancel() {
        this.status = ExecutionTaskStatus.CANCELLED.getCode();
        this.endTime = LocalDateTime.now();
    }

    /**
     * 完成任务
     * Complete task
     *
     * @param passCount Pass count
     * @param failCount Fail count
     * @param skipCount Skip count
     */
    public void complete(int passCount, int failCount, int skipCount) {
        this.passCount = passCount;
        this.failCount = failCount;
        this.skipCount = skipCount;
        this.status = ExecutionTaskStatus.COMPLETED.getCode();
        this.progress = 100;
        this.endTime = LocalDateTime.now();
        this.durationMs = java.time.Duration.between(this.startTime, this.endTime).toMillis();
    }

    /**
     * 任务失败
     * Mark task as failed
     *
     * @param errorMessage Error message
     */
    public void fail(String errorMessage) {
        this.status = ExecutionTaskStatus.FAILED.getCode();
        this.endTime = LocalDateTime.now();
    }

    /**
     * 更新进度
     * Update progress
     *
     * @param progress 进度
     *                 Progress
     */
    public void updateProgress(int progress) {
        this.progress = progress;
    }

    /**
     * 获取用例关联列表
     * Get case relation list
     *
     * @return 不可变用例关联列表
     * Unmodifiable case relation list
     */
    public List<TaskCaseRelation> getCaseRelations() {
        return Collections.unmodifiableList(this.caseRelations);
    }

}

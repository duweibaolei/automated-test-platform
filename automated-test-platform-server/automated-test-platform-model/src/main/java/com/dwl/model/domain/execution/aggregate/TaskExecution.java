package com.dwl.model.domain.execution.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.execution.ExecutionTaskStatus;
import com.dwl.model.domain.execution.entity.ExecutionStepResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 任务执行记录聚合根
 * <p>
 * Task Execution Record Aggregate Root
 * <p>
 * 执行管控域的聚合根, 记录单条用例在任务中的执行实例
 * 包含步骤级别的详细执行结果
 * <p>
 * Aggregate root of the Execution Control domain, recording execution instances
 * of a single case in a task. Contains step-level detailed execution results.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("task_execution")
@Schema(description = """
        任务执行记录聚合根
        Task Execution Record Aggregate Root
        """)
public class TaskExecution extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            执行记录 ID
            Execution record ID
            """)
    private Long id;

    @Schema(description = """
            Task ID
            """, example = "1")
    private Long taskId;

    @Schema(description = """
            Case ID
            """, example = "1")
    private Long caseId;

    @Schema(description = """
            Execution node ID
            """)
    private Long nodeId;

    @Schema(description = """
            重试序号
            Retry index (0 for first attempt)
            """, example = "0")
    private Integer retryIndex;

    @Schema(description = """
            Status
            """, example = "pending",
            implementation = ExecutionTaskStatus.class)
    private String status;

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
            失败步骤序号
            Failed step number
            """)
    private Integer failedStep;

    @Schema(description = """
            错误信息
            Error message
            """)
    private String errorMessage;

    @Schema(description = """
            失败截图 URL
            Screenshot URL on failure
            """)
    private String screenshotUrl;

    @Schema(description = """
            执行录像 URL
            Video recording URL
            """)
    private String videoUrl;

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
    private transient List<ExecutionStepResult> stepResults = new ArrayList<>();

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建任务执行记录
     * Factory Method: Create task execution record
     *
     * @param taskId     Task ID
     * @param caseId     Case ID
     * @param nodeId     Execution node ID
     * @param retryIndex 重试序号
     *                   Retry index
     * @return 新任务执行记录
     * New task execution record
     */
    public static TaskExecution create(Long taskId, Long caseId, Long nodeId, Integer retryIndex) {
        return TaskExecution.builder()
                .taskId(taskId).caseId(caseId).nodeId(nodeId).retryIndex(retryIndex)
                .status(ExecutionTaskStatus.PENDING.getCode()).stepResults(new ArrayList<>()).build();
    }

    /**
     * 开始执行
     * Start execution
     */
    public void start() {
        this.status = ExecutionTaskStatus.RUNNING.getCode();
        this.startTime = LocalDateTime.now();
    }

    /**
     * 通过
     * Pass
     */
    public void pass() {
        this.status = ExecutionTaskStatus.PAUSED.getCode();
        finish();
    }

    /**
     * 失败
     * Fail
     *
     * @param failedStep    失败步骤
     *                      Failed step
     * @param errorMessage  Error message
     * @param screenshotUrl 截图 URL
     *                      Screenshot URL
     */
    public void fail(Integer failedStep, String errorMessage, String screenshotUrl) {
        this.status = ExecutionTaskStatus.FAILED.getCode();
        this.failedStep = failedStep;
        this.errorMessage = errorMessage;
        this.screenshotUrl = screenshotUrl;
        finish();
    }

    /**
     * 跳过
     * Skip
     */
    public void skip() {
        this.status = ExecutionTaskStatus.SKIPPED.getCode();
        finish();
    }

    /**
     * 错误
     * Error
     *
     * @param errorMessage Error message
     */
    public void error(String errorMessage) {
        this.status = ExecutionTaskStatus.ERROR.getCode();
        this.errorMessage = errorMessage;
        finish();
    }

    /**
     * 完成执行
     * Finish execution
     */
    private void finish() {
        this.endTime = LocalDateTime.now();
        Optional.ofNullable(this.stepResults).ifPresent(status -> this.durationMs = Duration.between(this.startTime, this.endTime).toMillis());
    }

    /**
     * 添加步骤结果
     * Add step result
     *
     * @param stepOrder    步骤序号
     *                     Step order
     * @param actionType   动作类型
     *                     Action type
     * @param assertType   断言类型
     *                     Assert type
     * @param assertPassed 断言是否通过
     *                     Assert passed
     * @param actualValue  实际值
     *                     Actual value
     * @param durationMs   耗时
     *                     Duration
     * @param errorMessage Error message
     * @param locatorUsed  使用的定位器
     *                     Locator used
     */
    public void addStepResult(Integer stepOrder, String actionType, String assertType, Integer assertPassed,
                              Integer actualValue, String durationMs, String errorMessage, String locatorUsed) {
        this.stepResults.add(ExecutionStepResult.create(this.id, stepOrder, actionType, assertType,
                assertPassed, actualValue, durationMs, errorMessage, locatorUsed));
    }

    /**
     * 获取步骤结果列表
     * Get step result list
     *
     * @return 不可变步骤结果列表
     * Unmodifiable step result list
     */
    public List<ExecutionStepResult> getStepResults() {
        return Collections.unmodifiableList(this.stepResults);
    }

}

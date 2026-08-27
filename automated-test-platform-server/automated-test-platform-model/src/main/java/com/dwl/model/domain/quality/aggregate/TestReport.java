package com.dwl.model.domain.quality.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.SelectedStatus;
import com.dwl.common.enums.SourceType;
import com.dwl.common.enums.quality.ReportStatus;
import com.dwl.common.enums.quality.ReportType;
import com.dwl.model.domain.quality.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 测试报告聚合根
 * <p>
 * Test Report Aggregate Root
 * <p>
 * 质量报表域的核心聚合根, 封装测试报告的业务规则
 * 包含: 统计数据、AI 分析、手动结论、执行记录关联、AI 根因分析、手动失败标记
 * <p>
 * Core aggregate root of the Quality Report domain, encapsulating test report business rules
 * <p>
 * Contains: statistics, AI analysis, manual conclusion, execution record relations,
 * AI root cause analysis, manual failure marks.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("test_report")
@Schema(description = """
        测试报告聚合根
        Test Report Aggregate Root
        """)
public class TestReport extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            报告 ID
            Report ID
            """)
    private Long id;

    @Schema(description = """
            报告编号
            Report number
            """, example = "RPT-20260101-0001")
    private String reportNo;

    @Schema(description = """
            关联任务 ID
            Associated task ID
            """, example = "1")
    private Long taskId;

    @Schema(description = """
            报告类型
            Report type: task-task report, link-link report, change-change report
            """, example = "task",
            implementation = ReportType.class)
    private String reportType;

    @Schema(description = """
            触发来源
            Trigger source
            """, example = "auto",
            implementation = SourceType.class)
    private String triggerSource;

    @Schema(description = """
            总用例数
            Total case count
            """, example = "10")
    private Integer totalCount;

    @Schema(description = """
            Pass count
            """, example = "8")
    private Integer passCount;

    @Schema(description = """
            Fail count
            """, example = "1")
    private Integer failCount;

    @Schema(description = """
            Skip count
            """, example = "1")
    private Integer skipCount;

    @Schema(description = """
            Pass rate (%)
            """, example = "80.00")
    private BigDecimal passRate;

    @Schema(description = """
            总执行耗时
            Total execution duration (milliseconds)
            """)
    private Long durationMs;

    @Schema(description = """
            AI 分析摘要
            AI analysis summary
            """)
    private String aiSummary;

    @Schema(description = """
            AI 修复建议
            AI fix suggestion
            """)
    private String aiSuggestion;

    @Schema(description = """
            AI 是否完成分析
            AI analysis completed
            """, example = "0",
            implementation = SelectedStatus.class)
    private Integer aiAnalyzed;

    @Schema(description = """
            手动调整的报告结论
            Manual adjusted report conclusion
            """)
    private String manualConclusion;

    @Schema(description = """
            手动补充测试备注
            Manual supplementary test remarks
            """)
    private String manualRemark;

    @Schema(description = """
            状态
            Status: draft-draft, published-published
            """, example = "draft",
            implementation = ReportStatus.class)
    private String status;

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
    private transient List<ReportExecutionRelation> executionRelations = new ArrayList<>();

    @Builder.Default
    private transient List<AiRootCause> aiRootCauses = new ArrayList<>();

    @Builder.Default
    private transient List<ManualFailureMark> manualFailureMarks = new ArrayList<>();

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建测试报告
     * <p>
     * Factory Method: Create test report
     *
     * @param reportNo      报告编号
     *                      Report number
     * @param taskId        关联任务 ID
     *                      Associated task ID
     * @param reportType    报告类型
     *                      Report type
     * @param triggerSource 触发来源
     *                      Trigger source
     * @param totalCount    总用例数
     *                      Total case count
     * @param passCount     通过数
     *                      Pass count
     * @param failCount     失败数
     *                      Fail count
     * @param skipCount     跳过数
     *                      Skip count
     * @param passRate      通过率
     *                      Pass rate
     * @param durationMs    总执行耗时
     *                      Total execution duration
     * @return 新测试报告
     * New test report
     */
    public static TestReport create(String reportNo, Long taskId, String reportType, String triggerSource,
                                    Integer totalCount, Integer passCount, Integer failCount, Integer skipCount,
                                    BigDecimal passRate, Long durationMs) {
        return TestReport.builder()
                .reportNo(reportNo).taskId(taskId).reportType(reportType).triggerSource(triggerSource)
                .totalCount(totalCount).passCount(passCount).failCount(failCount).skipCount(skipCount)
                .passRate(passRate).durationMs(durationMs).aiAnalyzed(SelectedStatus.NOT_SELECTED.getValue())
                .status(ReportStatus.DRAFT.getCode())
                .executionRelations(new ArrayList<>()).aiRootCauses(new ArrayList<>()).manualFailureMarks(new ArrayList<>())
                .build();
    }

    /**
     * 创建任务报告
     * <p>
     * Create task report
     *
     * @param reportNo      报告编号
     *                      Report number
     * @param taskId        关联任务 ID
     *                      Associated task ID
     * @param triggerSource 触发来源
     *                      Trigger source
     * @param totalCount    总用例数
     *                      Total case count
     * @param passCount     通过数
     *                      Pass count
     * @param failCount     失败数
     *                      Fail count
     * @param skipCount     跳过数
     *                      Skip count
     * @param passRate      通过率
     *                      Pass rate
     * @param durationMs    总执行耗时
     *                      Total execution duration
     * @return 任务报告
     * Task report
     */
    public static TestReport createTaskReport(String reportNo, Long taskId, String triggerSource,
                                              Integer totalCount, Integer passCount, Integer failCount,
                                              Integer skipCount, BigDecimal passRate, Long durationMs) {
        return create(reportNo, taskId, ReportType.TASK.getCode(), triggerSource,
                totalCount, passCount, failCount, skipCount, passRate, durationMs);
    }

    /**
     * 添加执行记录关联
     * <p>
     * Add execution relation
     *
     * @param executionId 执行记录 ID
     *                    Execution record ID
     */
    public void addExecutionRelation(Long executionId) {
        this.executionRelations.add(ReportExecutionRelation.create(this.id, executionId));
    }

    /**
     * 添加 AI 根因分析
     * <p>
     * Add AI root cause
     *
     * @param executionId    执行记录 ID
     *                       Execution record ID
     * @param possibleCauses 可能原因
     *                       Possible causes
     * @param confidence     置信度
     *                       Confidence
     * @param fixSuggestion  修复建议
     *                       Fix suggestion
     * @param modelVersion   模型版本
     *                       Model version
     */
    public void addAiRootCause(Long executionId, String possibleCauses, Integer confidence, String fixSuggestion, String modelVersion) {
        this.aiRootCauses.add(AiRootCause.create(executionId, this.id, possibleCauses, confidence, fixSuggestion, modelVersion));
        this.aiAnalyzed = SelectedStatus.SELECTED.getValue();
    }

    /**
     * 添加手动失败标记
     * <p>
     * Add manual failure mark
     *
     * @param executionId   执行记录 ID
     *                      Execution record ID
     * @param failureReason 失败原因
     *                      Failure reason
     * @param description   描述
     *                      Description
     * @param markedBy      标记人 ID
     *                      Marked by user ID
     */
    public void addManualFailureMark(Long executionId, String failureReason, String description, Long markedBy) {
        this.manualFailureMarks.add(ManualFailureMark.create(executionId, failureReason, description, markedBy));
    }

    /**
     * 更新手动结论
     * <p>
     * Update manual conclusion
     *
     * @param conclusion 结论
     *                   Conclusion
     * @param remark     备注
     *                   Remark
     */
    public void updateManualConclusion(String conclusion, String remark) {
        this.manualConclusion = conclusion;
        this.manualRemark = remark;
    }

    /**
     * 发布报告
     * <p>
     * Publish report
     */
    public void publish() {
        if (!ReportStatus.DRAFT.getCode().equals(this.status)) {
            throw new IllegalStateException("Only draft reports can be published, current status: " + this.status);
        }
        this.status = ReportStatus.PUBLISHED.getCode();
    }

    /**
     * 获取执行记录关联列表
     * <p>
     * Get execution relation list
     *
     * @return 不可变执行记录关联列表
     * Unmodifiable execution relation list
     */
    public List<ReportExecutionRelation> getExecutionRelations() {
        return Collections.unmodifiableList(this.executionRelations);
    }

    /**
     * 获取 AI 根因分析列表
     * <p>
     * Get AI root cause list
     *
     * @return 不可变 AI 根因分析列表
     * Unmodifiable AI root cause list
     */
    public List<AiRootCause> getAiRootCauses() {
        return Collections.unmodifiableList(this.aiRootCauses);
    }

    /**
     * 获取手动失败标记列表
     * <p>
     * Get manual failure mark list
     *
     * @return 不可变手动失败标记列表
     * Unmodifiable manual failure mark list
     */
    public List<ManualFailureMark> getManualFailureMarks() {
        return Collections.unmodifiableList(this.manualFailureMarks);
    }

}

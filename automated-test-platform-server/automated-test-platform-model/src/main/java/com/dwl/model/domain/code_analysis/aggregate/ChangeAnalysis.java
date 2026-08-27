package com.dwl.model.domain.code_analysis.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.code_analysis.RiskLevel;
import com.dwl.common.enums.execution.ExecutionTaskStatus;
import com.dwl.common.enums.SourceType;
import com.dwl.common.exception.code_analysis.CodeAnalysisException;
import com.dwl.model.domain.code_analysis.entity.AffectedScope;
import com.dwl.model.domain.code_analysis.entity.ChangeAnalysisCommit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dwl.common.enums.SelectedStatus;

/**
 * 变更分析聚合根
 * <p>
 * Change Analysis Aggregate Root
 * <p>
 * 代码分析域的核心聚合根, 封装代码变更分析的业务规则
 * <p>
 * 包含：分析编号、仓库 ID、分支、提交范围、风险等级、AI 分析结果、影响范围、关联提交
 * <p>
 * Core aggregate root of the Code Analysis domain, encapsulating code change analysis business rules
 * <p>
 * Contains: analysis number, repository ID, branch, commit range, risk level, AI analysis results,
 * affected scope, associated commits
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("change_analysis")
@Schema(description = """
        变更分析聚合根
        Change Analysis Aggregate Root
        """)
public class ChangeAnalysis extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            分析 ID
            Analysis ID
            """)
    private Long id;

    @Schema(description = """
            分析编号
            Analysis number
            """, example = "CA-20260101-0001")
    private String analysisNo;

    @Schema(description = """
            仓库 ID
            Repository ID
            """, example = "1")
    private Long repoId;

    @Schema(description = """
            分支名
            Branch name
            """, example = "main")
    private String branch;

    @Schema(description = """
            起始 Commit Hash
            Start commit hash
            """)
    private String startCommitHash;

    @Schema(description = """
            截止 Commit Hash
            End commit hash
            """)
    private String endCommitHash;

    @Schema(description = """
            触发来源
            Trigger source
            """,
            example = "auto",
            implementation = SourceType.class)
    private String triggerSource;

    @Schema(description = """
            手动触发用户 ID
            Manual trigger user ID
            """)
    private Long triggerUserId;

    @Schema(description = """
            AI 风险等级
            AI risk level
            """,
            example = "low",
            implementation = RiskLevel.class)
    private String riskLevel;

    @Schema(description = """
            手动调整风险等级
            Manual adjusted risk level
            """, example = "low",
            implementation = RiskLevel.class)
    private String riskLevelManual;

    @Schema(description = """
            风险等级调整原因
            Risk level adjustment reason
            """)
    private String riskAdjustReason;

    @Schema(description = """
            AI 变更摘要
            AI change summary
            """)
    private String aiSummary;

    @Schema(description = """
            AI 测试建议
            AI test suggestion
            """)
    private String aiTestSuggestion;

    @Schema(description = """
            手动补充变更说明
            Manual supplementary change description
            """)
    private String manualDescription;

    @Schema(description = """
            Status
            """,
            example = "completed",
            implementation = ExecutionTaskStatus.class)
    private String status;

    @Schema(description = """
            Logical delete flag
            """,
            example = "0",
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

    /**
     * 影响范围列表
     * Affected scope list
     */
    @Builder.Default
    private transient List<AffectedScope> affectedScopes = new ArrayList<>();

    /**
     * 关联提交列表
     * Associated commit list
     */
    @Builder.Default
    private transient List<ChangeAnalysisCommit> analysisCommits = new ArrayList<>();

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法：创建变更分析
     * <p>
     * Factory Method: Create change analysis
     *
     * @param analysisNo      分析编号
     *                        Analysis number
     * @param repoId          仓库 ID
     *                        Repository ID
     * @param branch          分支名
     *                        Branch name
     * @param startCommitHash 起始 Commit Hash
     *                        Start commit hash
     * @param endCommitHash   截止 Commit Hash
     *                        End commit hash
     * @param triggerSource   触发来源
     *                        Trigger source
     * @param triggerUserId   手动触发用户 ID
     *                        Manual trigger user ID
     * @return 新变更分析
     * New change analysis
     */
    public static ChangeAnalysis create(String analysisNo, Long repoId, String branch,
                                        String startCommitHash, String endCommitHash,
                                        String triggerSource, Long triggerUserId) {
        return ChangeAnalysis.builder()
                .analysisNo(analysisNo)
                .repoId(repoId)
                .branch(branch)
                .startCommitHash(startCommitHash)
                .endCommitHash(endCommitHash)
                .triggerSource(triggerSource)
                .triggerUserId(triggerUserId)
                .status(ExecutionTaskStatus.PENDING.getCode())
                .affectedScopes(new ArrayList<>())
                .analysisCommits(new ArrayList<>())
                .build();
    }

    /**
     * 开始分析
     * Start analysis
     */
    public void startAnalysis() {
        if (!ExecutionTaskStatus.PENDING.getCode().equals(this.status)) {
            throw CodeAnalysisException.analysisStatusError(this.status, ExecutionTaskStatus.PENDING.getCode());
        }
        this.status = ExecutionTaskStatus.RUNNING.getCode();
    }

    /**
     * 完成分析
     * Complete analysis
     *
     * @param riskLevel        AI 风险等级
     *                         AI risk level
     * @param aiSummary        AI 变更摘要
     *                         AI change summary
     * @param aiTestSuggestion AI 测试建议
     *                         AI test suggestion
     */
    public void completeAnalysis(String riskLevel, String aiSummary, String aiTestSuggestion) {
        if (!ExecutionTaskStatus.RUNNING.getCode().equals(this.status)) {
            throw CodeAnalysisException.analysisStatusError(this.status, ExecutionTaskStatus.RUNNING.getCode());
        }
        this.riskLevel = riskLevel;
        this.aiSummary = aiSummary;
        this.aiTestSuggestion = aiTestSuggestion;
        this.status = ExecutionTaskStatus.COMPLETED.getCode();
    }

    /**
     * 分析失败
     * Mark analysis as failed
     */
    public void failAnalysis() {
        if (!ExecutionTaskStatus.RUNNING.getCode().equals(this.status)) {
            throw CodeAnalysisException.analysisStatusError(this.status, ExecutionTaskStatus.RUNNING.getCode());
        }
        this.status = ExecutionTaskStatus.FAILED.getCode();
    }

    /**
     * 人工调整风险等级
     * Manually adjust risk level
     *
     * @param riskLevelManual 手动调整风险等级
     *                        Manual adjusted risk level
     * @param reason          调整原因
     *                        Adjustment reason
     */
    public void adjustRiskLevel(String riskLevelManual, String reason) {
        this.riskLevelManual = riskLevelManual;
        this.riskAdjustReason = reason;
    }

    /**
     * 添加影响范围
     * Add affected scope
     *
     * @param scopeType 范围类型
     *                  Scope type
     * @param scopeName 范围名称
     *                  Scope name
     * @param scopePath 范围路径
     *                  Scope path
     */
    public void addAffectedScope(String scopeType, String scopeName, String scopePath) {
        this.affectedScopes.add(AffectedScope.create(this.id, scopeType, scopeName, scopePath));
    }

    /**
     * 选入/取消回归范围
     * Toggle selected for regression
     *
     * @param scopeId  范围 ID
     *                 Scope ID
     * @param selected 是否选入
     *                 Selected or not
     */
    public void toggleRegression(Long scopeId, boolean selected) {
        this.affectedScopes.stream()
                .filter(s -> scopeId.equals(s.getId()))
                .findFirst()
                .ifPresent(s -> s.setSelectedForRegression(selected ? SelectedStatus.SELECTED.getValue() : SelectedStatus.NOT_SELECTED.getValue()));
    }

    /**
     * 关联提交
     * Associate commit
     *
     * @param commitId Commit ID
     */
    public void associateCommit(Long commitId) {
        this.analysisCommits.add(ChangeAnalysisCommit.create(this.id, commitId));
    }

    /**
     * 获取影响范围列表
     * Get affected scope list
     *
     * @return 不可变影响范围列表
     * Unmodifiable affected scope list
     */
    public List<AffectedScope> getAffectedScopes() {
        return Collections.unmodifiableList(this.affectedScopes);
    }

    /**
     * 获取关联提交列表
     * Get associated commit list
     *
     * @return 不可变关联提交列表
     * Unmodifiable associated commit list
     */
    public List<ChangeAnalysisCommit> getAnalysisCommits() {
        return Collections.unmodifiableList(this.analysisCommits);
    }

}

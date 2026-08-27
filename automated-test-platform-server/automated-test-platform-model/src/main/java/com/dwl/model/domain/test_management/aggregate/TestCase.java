package com.dwl.model.domain.test_management.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.SourceType;
import com.dwl.common.enums.CaseStatus;
import com.dwl.common.enums.testmanagement.HandlingStatus;
import com.dwl.model.domain.test_management.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 测试用例聚合根
 * <p>
 * Test Case Aggregate Root
 * <p>
 * 测试管理域的核心聚合根, 封装测试用例的业务规则
 * 包含: 用例基本信息、步骤列表、版本快照、标签关联、链路关联、分析关联
 * <p>
 * Core aggregate root of the Test Management domain, encapsulating test case business rules
 * <p>
 * Contains: basic case information, step list, version snapshot, tag association,
 * link association, analysis association.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("test_case")
@Schema(description = """
        测试用例聚合根
        Test Case Aggregate Root
        """)
public class TestCase extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            Case ID
            """)
    private Long id;

    @Schema(description = """
            Case number
            """, example = "TC-0001")
    private String caseNo;

    @Schema(description = """
            Case name
            """, example = "用户登录 - 正常流程")
    private String caseName;

    @Schema(description = """
            Module name
            """, example = "用户管理")
    private String moduleName;

    @Schema(description = """
            Source
            """, example = "manual",
            implementation = SourceType.class)
    private String source;

    @Schema(description = """
            Status
            """, example = "active",
            implementation = CaseStatus.class)
    private String status;

    @Schema(description = """
            健康度评分
            Health score (0-100)
            """, example = "100")
    private Integer healthScore;

    @Schema(description = """
            优先级
            Priority: P0-P0, P1-P1, P2-P2
            """, example = "P2")
    private String priority;

    @Version
    @Schema(description = """
            版本号
            Version number (optimistic lock)
            """, example = "1")
    private Integer version;

    @Schema(description = """
            用例描述
            Case description
            """)
    private String description;

    @Schema(description = """
            前置条件
            Pre-condition
            """)
    private String preCondition;

    @Schema(description = """
            执行环境 ID
            Execution environment ID
            """)
    private Long envId;

    @Schema(description = """
            创建人 ID
            Creator user ID
            """)
    private Long createdBy;

    @Schema(description = """
            最后修改人 ID
            Last modifier user ID
            """)
    private Long lastModifiedBy;

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
    private transient List<CaseStep> steps = new ArrayList<>();

    @Builder.Default
    private transient List<CaseVersion> versions = new ArrayList<>();

    @Builder.Default
    private transient List<CaseTagRelation> tagRelations = new ArrayList<>();

    @Builder.Default
    private transient List<CaseLinkRelation> linkRelations = new ArrayList<>();

    @Builder.Default
    private transient List<CaseAnalysisRelation> analysisRelations = new ArrayList<>();

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建测试用例
     * <p>
     * Factory Method: Create test case
     *
     * @param caseNo       Case number
     * @param caseName     Case name
     * @param moduleName   所属模块
     *                     Module name
     * @param source       Source
     * @param priority     优先级
     *                     Priority
     * @param description  用例描述
     *                     Case description
     * @param preCondition 前置条件
     *                     Pre-condition
     * @param envId        执行环境 ID
     *                     Execution environment ID
     * @param createdBy    Creator user ID
     * @return New test case
     */
    public static TestCase create(String caseNo, String caseName, String moduleName,
                                  String source, String priority, String description,
                                  String preCondition, Long envId, Long createdBy) {
        return TestCase.builder()
                .caseNo(caseNo).caseName(caseName).moduleName(moduleName)
                .source(source).priority(priority).description(description)
                .preCondition(preCondition).envId(envId).createdBy(createdBy)
                .lastModifiedBy(createdBy).status(CaseStatus.DRAFT.getCode()).healthScore(100).version(1)
                .steps(new ArrayList<>()).versions(new ArrayList<>())
                .tagRelations(new ArrayList<>()).linkRelations(new ArrayList<>())
                .analysisRelations(new ArrayList<>())
                .build();
    }

    /**
     * Add step
     *
     * @param stepOrder   步骤顺序
     *                    Step order
     * @param elementId   Element ID
     * @param actionType  动作类型
     *                    Action type
     * @param actionValue 动作值
     *                    Action value
     * @param assertType  断言类型
     *                    Assert type
     * @param assertValue 断言值
     *                    Assert value
     * @param waitTimeout 等待超时
     *                    Wait timeout
     */
    public void addStep(Integer stepOrder, Long elementId, String actionType, String actionValue,
                        String assertType, String assertValue, Integer waitTimeout) {
        this.steps.add(CaseStep.create(this.id, stepOrder, elementId, actionType, actionValue,
                assertType, assertValue, waitTimeout));
    }

    /**
     * 创建版本快照
     * Create version snapshot
     *
     * @param snapshotJson  快照 JSON
     *                      Snapshot JSON
     * @param changeSummary 变更摘要
     *                      Change summary
     * @param modifiedBy    修改人 ID
     *                      Modifier user ID
     */
    public void createVersion(String snapshotJson, String changeSummary, Long modifiedBy) {
        this.versions.add(CaseVersion.create(this.id, this.version, snapshotJson, changeSummary, modifiedBy));
    }

    /**
     * 发布用例
     * Publish case
     */
    public void publish() {
        this.status = CaseStatus.ACTIVE.getCode();
    }

    /**
     * 禁用用例
     * Disable case
     */
    public void disable() {
        this.status = CaseStatus.DISABLED.getCode();
    }

    /**
     * 标记为不稳定
     * Mark as unstable
     */
    public void markUnstable() {
        this.status = CaseStatus.UNSTABLE.getCode();
    }

    /**
     * 标记为受分析影响
     * Mark as affected by analysis
     *
     * @param analysisId   分析 ID
     *                     Analysis ID
     * @param affectedType 影响类型
     *                     Affected type
     */
    public void markAffectedByAnalysis(Long analysisId, String affectedType) {
        this.analysisRelations.add(CaseAnalysisRelation.create(this.id, analysisId, affectedType, HandlingStatus.PENDING.getCode()));
    }

    /**
     * 获取步骤列表
     * Get step list
     *
     * @return 不可变步骤列表
     * Unmodifiable step list
     */
    public List<CaseStep> getSteps() {
        return Collections.unmodifiableList(this.steps);
    }

    /**
     * 获取版本列表
     * Get version list
     *
     * @return 不可变版本列表
     * Unmodifiable version list
     */
    public List<CaseVersion> getVersions() {
        return Collections.unmodifiableList(this.versions);
    }

    /**
     * 获取标签关联列表
     * Get tag relation list
     *
     * @return 不可变标签关联列表
     * Unmodifiable tag relation list
     */
    public List<CaseTagRelation> getTagRelations() {
        return Collections.unmodifiableList(this.tagRelations);
    }

    /**
     * 获取链路关联列表
     * Get link relation list
     *
     * @return 不可变链路关联列表
     * Unmodifiable link relation list
     */
    public List<CaseLinkRelation> getLinkRelations() {
        return Collections.unmodifiableList(this.linkRelations);
    }

    /**
     * 获取分析关联列表
     * Get analysis relation list
     *
     * @return 不可变分析关联列表
     * Unmodifiable analysis relation list
     */
    public List<CaseAnalysisRelation> getAnalysisRelations() {
        return Collections.unmodifiableList(this.analysisRelations);
    }

}

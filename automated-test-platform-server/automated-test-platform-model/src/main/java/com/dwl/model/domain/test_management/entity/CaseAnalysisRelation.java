package com.dwl.model.domain.test_management.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 用例与分析关联实体
 * Case-Analysis Association Entity
 * <p>
 * TestCase 聚合根内的实体, 用例与变更分析的多对多关联, 包含受影响类型和处理状态
 * Entity within TestCase aggregate root, many-to-many association between cases
 * and change analyses, including affected type and handling status.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("case_analysis_relation")
@Schema(description = """
        用例与分析关联实体
        Case-Analysis Association Entity
        """)
public class CaseAnalysisRelation extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    @Schema(description = """
            关联 ID
            Association ID
            """)
    private Long id;

    @Schema(description = """
            用例 ID
            Case ID
            """, example = "1")
    private Long caseId;

    @Schema(description = """
            分析 ID
            Analysis ID
            """, example = "1")
    private Long analysisId;

    @Schema(description = """
            受影响类型
            Affected type: added-modified, modified-modified, deleted-deleted, risk_affected-risk affected
            """, example = "modified")
    private String affectedType;

    @Schema(description = """
            处理状态
            Handling status: pending-pending, tested-tested, bypassed-bypassed
            """, example = "pending")
    private String handlingStatus;

    @Schema(description = """
            逻辑删除标识
            Logical delete flag: 0-not deleted, 1-deleted
            """)
    private Integer isDelete;

    @Schema(description = """
            创建时间
            Create time
            """)
    private LocalDateTime createdAt;

    @Schema(description = """
            更新时间
            Update time
            """)
    private LocalDateTime updatedAt;

    /**
     * 工厂方法: 创建用例与分析关联
     * Factory Method: Create case-analysis relation
     *
     * @param caseId         用例 ID / Case ID
     * @param analysisId     分析 ID / Analysis ID
     * @param affectedType   受影响类型 / Affected type
     * @param handlingStatus 处理状态 / Handling status
     * @return 新关联实体 / New association entity
     */
    public static CaseAnalysisRelation create(Long caseId, Long analysisId, String affectedType, String handlingStatus) {
        return CaseAnalysisRelation.builder()
                .caseId(caseId).analysisId(analysisId)
                .affectedType(affectedType).handlingStatus(handlingStatus).build();
    }

    /**
     * 标记为已测试
     * Mark as tested
     */
    public void markTested() {
        this.handlingStatus = "tested";
    }

    /**
     * 标记为已绕过
     * Mark as bypassed
     */
    public void markBypassed() {
        this.handlingStatus = "bypassed";
    }

}

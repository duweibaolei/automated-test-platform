package com.dwl.model.domain.test_management.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.testmanagement.AffectedType;
import com.dwl.common.enums.testmanagement.HandlingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 用例与分析关联实体
 * <p>
 * Case-Analysis Association Entity
 * <p>
 * TestCase 聚合根内的实体，用例与变更分析的多对多关联，包含受影响类型和处理状态
 * <p>
 * Entity within TestCase aggregate root, many-to-many association between cases
 * and change analyses, including affected type and handling status
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

    @Serial
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
            Affected type
            """, example = "modified",
            implementation = AffectedType.class)
    private String affectedType;

    @Schema(description = """
            处理状态
            Handling status
            """, example = "pending", implementation = HandlingStatus.class)
    private String handlingStatus;

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

    /**
     * 工厂方法：创建用例与分析关联
     * <p>
     * Factory Method: Create case-analysis relation
     *
     * @param caseId         Case ID
     * @param analysisId     分析 ID
     *                       Analysis ID
     * @param affectedType   受影响类型
     *                       Affected type
     * @param handlingStatus 处理状态
     *                       Handling status
     * @return 新关联实体
     * New association entity
     * @throws IllegalArgumentException 当受影响类型或处理状态不合法时
     *                                  When affected type or handling status is invalid
     */
    public static CaseAnalysisRelation create(Long caseId, Long analysisId, String affectedType, String handlingStatus) {
        if (!AffectedType.exists(affectedType)) {
            throw new IllegalArgumentException("Invalid affected type: " + affectedType);
        }

        if (Objects.nonNull(handlingStatus) && !HandlingStatus.exists(handlingStatus)) {
            throw new IllegalArgumentException("Invalid handling status: " + handlingStatus);
        }

        return CaseAnalysisRelation.builder()
                .caseId(caseId).analysisId(analysisId)
                .affectedType(affectedType).handlingStatus(handlingStatus).build();
    }

    /**
     * 标记为已测试
     * Mark as tested
     */
    public void markTested() {
        this.handlingStatus = HandlingStatus.TESTED.getCode();
    }

    /**
     * 标记为已绕过
     * Mark as bypassed
     */
    public void markBypassed() {
        this.handlingStatus = HandlingStatus.BYPASSED.getCode();
    }

    /**
     * 更新处理状态
     * <p>
     * Update handling status
     *
     * @param newStatus 新处理状态
     *                  New handling status
     * @throws IllegalArgumentException 当处理状态不合法时
     *                                  When handling status is invalid
     */
    public void updateHandlingStatus(String newStatus) {
        if (!HandlingStatus.exists(newStatus)) {
            throw new IllegalArgumentException("Invalid handling status: " + newStatus);
        }
        this.handlingStatus = newStatus;
    }

}

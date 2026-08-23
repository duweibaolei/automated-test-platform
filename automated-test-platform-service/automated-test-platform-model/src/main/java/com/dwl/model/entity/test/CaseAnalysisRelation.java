package com.dwl.model.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 用例与分析关联实体
 * Case-Analysis Association Entity
 * 对应表 case_analysis_relation，用例与变更分析的多对多关联。
 * Maps to table case_analysis_relation, many-to-many association between cases and change analyses.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 18:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("case_analysis_relation")
@Schema(description = """
        用例与分析关联
        Case-Analysis Association
        """)
public class CaseAnalysisRelation extends BaseEntity {

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
            Affected type: added modified deleted risk_affected
            """, example = "modified")
    private String affectedType;

    @Schema(description = """
            处理状态
            Handling status: pending tested bypassed
            """, example = "pending")
    private String handlingStatus;

}

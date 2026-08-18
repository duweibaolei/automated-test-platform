package com.dwl.model.entity.code;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 变更分析实体
 * Change Analysis Entity
 * 对应表 change_analysis,记录代码变更分析的结果和风险评定信息
 * Maps to table change_analysis, recording code change
 * analysis results and risk assessment information
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 18:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("change_analysis")
@Schema(description = """
        变更分析
        Change Analysis
        """)
public class ChangeAnalysis extends BaseEntity {

    @Schema(description = """
            分析编号
            Analysis number: CA-YYYYMMDD-NNNN
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
            起始 Commit
            Start commit hash (specified in manual analysis)
            """)
    private String startCommitHash;

    @Schema(description = """
            截止 Commit
            End commit hash (specified in manual analysis)
            """)
    private String endCommitHash;

    @Schema(description = """
            触发来源
            Trigger source: auto-Webhook automatic, manual-manual trigger
            """, example = "auto")
    private String triggerSource;

    @Schema(description = """
            手动触发用户 ID
            Manual trigger user ID
            """)
    private Long triggerUserId;

    @Schema(description = """
            AI 风险等级
            AI risk level: high/medium/low
            """, example = "low")
    private String riskLevel;

    @Schema(description = """
            手动调整风险等级
            Manual adjusted risk level: high/medium/low
            """)
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
            状态
            Status: running/completed/failed
            """, example = "completed")
    private String status;

}

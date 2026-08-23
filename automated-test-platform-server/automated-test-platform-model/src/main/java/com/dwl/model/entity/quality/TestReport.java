package com.dwl.model.entity.quality;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

/**
 * 测试报告实体
 * Test Report Entity
 * 对应表 test_report，管理测试报告的统计、AI 分析和结论信息。
 * Maps to table test_report, managing test report statistics, AI analysis, and conclusion information.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 11:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("test_report")
@Schema(description = """
        测试报告
        Test Report
        """)
public class TestReport extends BaseEntity {

    @Schema(description = """
            报告编号
            Report number: RPT-YYYYMMDD-NNNN
            """, example = "RPT-20260101-0001")
    private String reportNo;

    @Schema(description = """
            关联任务 ID
            Associated task ID
            """, example = "1")
    private Long taskId;

    @Schema(description = """
            报告类型
            Report type: task/link/change
            """, example = "task")
    private String reportType;

    @Schema(description = """
            触发来源
            Trigger source: auto/manual
            """, example = "auto")
    private String triggerSource;

    @Schema(description = """
            总用例数
            Total case count
            """, example = "10")
    private Integer totalCount;

    @Schema(description = """
            通过数
            Pass count
            """, example = "8")
    private Integer passCount;

    @Schema(description = """
            失败数
            Fail count
            """, example = "1")
    private Integer failCount;

    @Schema(description = """
            跳过数
            Skip count
            """, example = "1")
    private Integer skipCount;

    @Schema(description = """
            通过率
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
            Whether AI has completed analysis: 1-yes, 0-no
            """, example = "0")
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
            Status: draft/published
            """, example = "draft")
    private String status;

}

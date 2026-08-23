package com.dwl.model.entity.quality;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 质量趋势日统计实体
 * Quality Daily Statistics Entity
 * 对应表 quality_daily_stats,按日聚合的质量度量统计数据
 * Maps to table quality_daily_stats, daily aggregated quality metric statistics
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 16:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("quality_daily_stats")
@Schema(description = """
        质量趋势日统计
        Quality Daily Statistics
        """)
public class QualityDailyStats extends BaseEntity {

    @Schema(description = """
            统计时间
            Statistics date
            """, example = "2026-01-01 00:00:00")
    private LocalDateTime statDate;

    @Schema(description = """
            总用例数
            Total case count
            """, example = "100")
    private Integer totalCases;

    @Schema(description = """
            执行用例数
            Total executed case count
            """, example = "80")
    private Integer totalExecuted;

    @Schema(description = """
            通过数
            Total passed count
            """, example = "72")
    private Integer totalPassed;

    @Schema(description = """
            失败数
            Total failed count
            """, example = "8")
    private Integer totalFailed;


    @Schema(description = """
            通过率(%)
            Pass rate (%)
            """, example = "90.00")
    private BigDecimal passRate;

    @Schema(description = """
            新增缺陷数
            New defect count
            """, example = "3")
    private Integer newDefects;

    @Schema(description = """
            解决缺陷数
            Resolved defect count
            """, example = "2")
    private Integer resolvedDefects;

    @Schema(description = """
            自动来源用例数
            Auto-sourced case count
            """, example = "40")
    private Integer autoCases;

    @Schema(description = """
            手动来源用例数
            Manual-sourced case count
            """, example = "50")
    private Integer manualCases;

    @Schema(description = """
            混合来源用例数
            Hybrid-sourced case count
            """, example = "10")
    private Integer hybridCases;


}

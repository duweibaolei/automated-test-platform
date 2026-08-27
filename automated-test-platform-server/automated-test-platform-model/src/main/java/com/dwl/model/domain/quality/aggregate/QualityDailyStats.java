package com.dwl.model.domain.quality.aggregate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.AggregateRoot;
import com.dwl.common.enums.DeletedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 质量趋势日统计聚合根
 * <p>
 * Quality Daily Statistics Aggregate Root
 * <p>
 * 质量报表域的聚合根, 按日聚合的质量度量统计数据
 * <p>
 * Aggregate root of the Quality Report domain, containing daily aggregated
 * quality metric statistics.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("quality_daily_stats")
@Schema(description = """
        质量趋势日统计聚合根
        Quality Daily Statistics Aggregate Root
        """)
public class QualityDailyStats extends AggregateRoot<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            统计 ID
            Statistics ID
            """)
    private Long id;

    @Schema(description = """
            统计日期
            Statistics date
            """, example = "2026-01-01 00:00:00")
    private LocalDateTime statDate;

    @Schema(description = """
            Total case count
            """, example = "100")
    private Integer totalCases;

    @Schema(description = """
            Total executed
            """, example = "80")
    private Integer totalExecuted;

    @Schema(description = """
            Total passed
            """, example = "72")
    private Integer totalPassed;

    @Schema(description = """
            Total failed
            """, example = "8")
    private Integer totalFailed;

    @Schema(description = """
            Pass rate (%)
            """, example = "90.00")
    private BigDecimal passRate;

    @Schema(description = """
            新增缺陷数
            New defects
            """, example = "3")
    private Integer newDefects;

    @Schema(description = """
            解决缺陷数
            Resolved defects
            """, example = "2")
    private Integer resolvedDefects;

    @Schema(description = """
            自动来源用例数
            Auto-generated cases
            """, example = "40")
    private Integer autoCases;

    @Schema(description = """
            手动来源用例数
            Manual cases
            """, example = "50")
    private Integer manualCases;

    @Schema(description = """
            混合来源用例数
            Hybrid cases
            """, example = "10")
    private Integer hybridCases;

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

    /* ================================================================
     * 业务方法
     * Business Methods
     * ================================================================ */

    /**
     * 工厂方法: 创建质量日统计
     * <p>
     * Factory Method: Create quality daily statistics
     *
     * @param statDate        统计日期
     *                        Statistics date
     * @param totalCases      总用例数
     *                        Total case count
     * @param totalExecuted   执行用例数
     *                        Total executed
     * @param totalPassed     通过数
     *                        Total passed
     * @param totalFailed     失败数
     *                        Total failed
     * @param passRate        通过率
     *                        Pass rate
     * @param newDefects      新增缺陷数
     *                        New defects
     * @param resolvedDefects 解决缺陷数
     *                        Resolved defects
     * @param autoCases       自动来源用例数
     *                        Auto-generated cases
     * @param manualCases     手动来源用例数
     *                        Manual cases
     * @param hybridCases     混合来源用例数
     *                        Hybrid cases
     * @return 新质量日统计
     * New quality daily statistics
     */
    public static QualityDailyStats create(LocalDateTime statDate, Integer totalCases, Integer totalExecuted,
                                           Integer totalPassed, Integer totalFailed, BigDecimal passRate,
                                           Integer newDefects, Integer resolvedDefects,
                                           Integer autoCases, Integer manualCases, Integer hybridCases) {
        return QualityDailyStats.builder()
                .statDate(statDate).totalCases(totalCases).totalExecuted(totalExecuted)
                .totalPassed(totalPassed).totalFailed(totalFailed).passRate(passRate)
                .newDefects(newDefects).resolvedDefects(resolvedDefects)
                .autoCases(autoCases).manualCases(manualCases).hybridCases(hybridCases).build();
    }

}

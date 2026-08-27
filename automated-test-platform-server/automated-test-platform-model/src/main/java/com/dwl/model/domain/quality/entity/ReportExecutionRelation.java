package com.dwl.model.domain.quality.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 报告与执行结果关联实体
 * <p>
 * Report-Execution Relation Entity
 * <p>
 * TestReport 聚合内的实体, 记录测试报告与执行结果的关联关系
 * <p>
 * Entity within the TestReport aggregate, recording the association
 * between test report and execution results
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("report_execution_relation")
@Schema(description = """
        报告与执行结果关联实体
        Report-Execution Relation Entity
        """)
public class ReportExecutionRelation extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            关联 ID
            Association ID
            """)
    private Long id;

    @Schema(description = """
            报告 ID
            Report ID
            """)
    private Long reportId;

    @Schema(description = """
            执行记录 ID
            Execution record ID
            """)
    private Long executionId;

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
     * 工厂方法: 创建报告与执行关联
     * <p>
     * Factory Method: Create report-execution relation
     *
     * @param reportId    报告 ID
     *                    Report ID
     * @param executionId 执行记录 ID
     *                    Execution record ID
     * @return 新关联
     * New relation
     */
    public static ReportExecutionRelation create(Long reportId, Long executionId) {
        return ReportExecutionRelation.builder()
                .reportId(reportId)
                .executionId(executionId)
                .build();
    }

}

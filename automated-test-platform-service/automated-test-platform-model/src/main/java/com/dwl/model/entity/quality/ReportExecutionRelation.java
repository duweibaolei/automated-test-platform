package com.dwl.model.entity.quality;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 报告与执行结果关联实体
 * Report-Execution Association Entity
 * 对应表 report_execution_relation,报告与执行记录的多对多关联
 * Maps to table report_execution_relation, many-to-many association between reports and execution records
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 14:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("report_execution_relation")
@Schema(description = """
        报告与执行结果关联
        Report-Execution Association
        """)
public class ReportExecutionRelation extends BaseEntity {

    @Schema(description = """
            报告 ID
            Report ID
            """, example = "1")
    private Long reportId;

    @Schema(description = """
            执行记录 ID
            Execution record ID
            """, example = "1")
    private Long executionId;
}

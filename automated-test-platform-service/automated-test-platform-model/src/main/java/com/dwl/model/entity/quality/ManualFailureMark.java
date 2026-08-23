package com.dwl.model.entity.quality;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 手动失败原因标记实体
 * Manual Failure Mark
 * 对应表 manual_failure_mark,人工对失败用例标记原因分类
 * Maps to table manual_failure_mark, manually marking failure reason categories for failed cases
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 15:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("manual_failure_mark")
@Schema(description = """
        手动失败原因标记
        Manual Failure Mark
        """)
public class ManualFailureMark extends BaseEntity {

    @Schema(description = """
            执行记录 ID
            Execution record ID
            """, example = "1")
    private Long executionId;

    @Schema(description = """
            失败原因: bug-业务缺陷 flaky-用例失败 env-环境问题
            Failure reason
            """, example = "bug")
    private String failureReason;

    @Schema(description = """
            补充说明
            Supplementary description
            """)
    private String description;

    @Schema(description = """
            标记人 ID
            Marker user ID
            """, example = "1")
    private Long markedBy;

    @Schema(description = """
            标记时间
            Marked timestamp
            """)
    private LocalDateTime markedAt;

}

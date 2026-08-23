package com.dwl.model.entity.quality;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * AI 根因分析实体
 * AI Root Cause Analysis Entity
 * 对应表 ai_root_cause,记录AI对失败用例的根因分析结果
 * Maps to table ai_root_cause, recording AI root cause analysis results for failed cases
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 15:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@TableName("ai_root_cause")
@Schema(description = """
        AI 根因分析
        AI Root Cause Analysis
        """)
public class AiRootCause extends BaseEntity {

    @Schema(description = """
            执行记录 ID
            Execution record ID
            """, example = "1")
    private Long executionId;

    @Schema(description = """
            报告 ID
            Repost ID
            """)
    private Long recordId;

    @Schema(description = """
            AI 分析可能原因
            AI analyzed possible cause
            """)
    private String possibleCauses;

    @Schema(description = """
            置信度(0-100)
            Confidence level(0-100)
            """, example = "85")
    private Integer confidence;

    @Schema(description = """
            修复建议
            Fix suggestion
            """)
    private String fixSuggestion;

    @Schema(description = """
            AI 模型版本
            AI model version
            """, example = "glm-5.2")
    private String modelVersion;

    @Schema(description = """
            分析时间
            Analyzed timestamp
            """)
    private LocalDateTime analyzedAt;

}

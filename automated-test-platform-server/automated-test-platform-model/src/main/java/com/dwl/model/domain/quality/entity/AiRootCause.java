package com.dwl.model.domain.quality.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * AI 根因分析实体
 * <p>
 * AI Root Cause Analysis Entity
 * <p>
 * TestReport 聚合内的实体, 记录 AI 对测试失败的根因分析结果
 * <p>
 * Entity within the TestReport aggregate, recording AI analysis results
 * for test failures root cause.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_root_cause")
@Schema(description = """
        AI 根因分析实体
        AI Root Cause Analysis Entity
        """)
public class AiRootCause extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            分析 ID
            Analysis ID
            """)
    private Long id;

    @Schema(description = """
            执行记录 ID
            Execution record ID
            """)
    private Long executionId;

    @Schema(description = """
            报告 ID
            Report ID
            """)
    private Long recordId;

    @Schema(description = """
            AI 分析可能原因
            AI analysis possible causes
            """)
    private String possibleCauses;

    @Schema(description = """
            置信度
            Confidence (0-100)
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
            Analyzed at
            """)
    private LocalDateTime analyzedAt;

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
     * 工厂方法: 创建 AI 根因分析
     * <p>
     * Factory Method: Create AI root cause analysis
     *
     * @param executionId    执行记录 ID
     *                       Execution record ID
     * @param recordId       报告 ID
     *                       Report ID
     * @param possibleCauses AI 分析可能原因
     *                       Possible causes
     * @param confidence     置信度
     *                       Confidence
     * @param fixSuggestion  修复建议
     *                       Fix suggestion
     * @param modelVersion   AI 模型版本
     *                       Model version
     * @return 新 AI 根因分析
     * New AI root cause analysis
     */
    public static AiRootCause create(Long executionId, Long recordId, String possibleCauses, Integer confidence,
                                     String fixSuggestion, String modelVersion) {
        return AiRootCause.builder()
                .executionId(executionId)
                .recordId(recordId)
                .possibleCauses(possibleCauses)
                .confidence(confidence)
                .fixSuggestion(fixSuggestion)
                .modelVersion(modelVersion)
                .analyzedAt(LocalDateTime.now())
                .build();
    }

}

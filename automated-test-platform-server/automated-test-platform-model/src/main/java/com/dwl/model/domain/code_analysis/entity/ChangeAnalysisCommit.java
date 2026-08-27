package com.dwl.model.domain.code_analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 变更分析与提交关联实体
 * <p>
 * Change Analysis-Commit Association Entity
 * <p>
 * ChangeAnalysis 聚合根内的实体, 表示变更分析与 Git 提交记录的多对多关联
 * 外部不能直接操作此实体, 必须通过 ChangeAnalysis 聚合根的方法
 * <p>
 * Entity within the ChangeAnalysis aggregate, representing the many-to-many
 * association between change analysis and Git commit records
 * <p>
 * External objects cannot directly operate this entity, must go through ChangeAnalysis methods
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("change_analysis_commit")
@Schema(description = """
        变更分析与提交关联实体
        Change Analysis-Commit Association Entity
        """)
public class ChangeAnalysisCommit extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            关联 ID
            Association ID
            """)
    private Long id;

    @Schema(description = """
            分析 ID
            Analysis ID
            """, example = "1")
    private Long analysisId;

    @Schema(description = """
            提交记录 ID
            Commit record ID
            """, example = "1")
    private Long commitId;

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
     * 工厂方法: 创建变更分析与提交关联
     * <p>
     * Factory Method: Create change analysis-commit association
     *
     * @param analysisId 分析 ID
     *                   Analysis ID
     * @param commitId   提交记录 ID
     *                   Commit record ID
     * @return 新关联
     * New association
     */
    public static ChangeAnalysisCommit create(Long analysisId, Long commitId) {
        return ChangeAnalysisCommit.builder()
                .analysisId(analysisId)
                .commitId(commitId)
                .build();
    }

}

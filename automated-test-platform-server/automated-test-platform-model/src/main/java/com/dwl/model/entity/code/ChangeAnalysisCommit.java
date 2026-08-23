package com.dwl.model.entity.code;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 变更分析与提交关联实体
 * Change Analysis-Commit Association Entity
 * 对应表 change_analysis_commit,变更分析与 Git 提交记录的多对多关联
 * Maps to table change_analysis_commit, many-to-many association
 * between change analyses and git commits
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-18 18:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("change_analysis_commit")
@Schema(description = """
        变更分析与提交关联
        Change Analysis-Commit Association
        """)
public class ChangeAnalysisCommit extends BaseEntity {

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

}

package com.dwl.model.entity.execute;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 任务与用例关联实体
 * Task-Case Association Entity
 * 对应表 task_case_relation,测试任务与用例的多对多关联
 * Maps to table task_case_relation, many-to-many association between test tasks and cases
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 13:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("task_case_relation")
@Schema(description = """
        任务与用例关联
        Task-Case Association
        """)
public class TaskCaseRelation extends BaseEntity {

    @Schema(description = """
            任务 ID
            Task ID
            """, example = "1")
    private Long taskId;

    @Schema(description = """
            用例 ID
            Case ID
            """, example = "1")
    private Long caseId;

}

package com.dwl.model.domain.execution.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 任务与用例关联实体
 * <p>
 * Task-Case Association Entity
 * <p>
 * TestTask 聚合根内的实体, 测试任务与用例的多对多关联
 * <p>
 * Entity within the TestTask aggregate, many-to-many association
 * between test task and test case.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("task_case_relation")
@Schema(description = """
        任务与用例关联实体
        Task-Case Association Entity
        """)
public class TaskCaseRelation extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            关联 ID
            Association ID
            """)
    private Long id;

    @Schema(description = """
            Task ID
            """, example = "1")
    private Long taskId;

    @Schema(description = """
            Case ID
            """, example = "1")
    private Long caseId;

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
     * 工厂方法: 创建任务与用例关联
     * Factory Method: Create task-case association
     *
     * @param taskId Task ID
     * @param caseId Case ID
     * @return 新关联
     * New association
     */
    public static TaskCaseRelation create(Long taskId, Long caseId) {
        return TaskCaseRelation.builder()
                .taskId(taskId)
                .caseId(caseId)
                .build();
    }

}

package com.dwl.model.domain.quality.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.quality.FailureReason;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 手动失败原因标记实体
 * Manual Failure Mark Entity
 * <p>
 * TestReport 聚合内的实体，记录手动标记的测试失败原因
 * Entity within the TestReport aggregate, recording manually marked
 * test failure reasons.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("manual_failure_mark")
@Schema(description = """
        手动失败原因标记实体
        Manual Failure Mark Entity
        """)
public class ManualFailureMark extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            标记 ID
            Mark ID
            """)
    private Long id;

    @Schema(description = """
            执行记录 ID
            Execution record ID
            """)
    private Long executionId;

    @Schema(description = """
            失败原因
            Failure reason:
            - bug: 真实缺陷，需要修复
            - flaky: 不稳定用例，需要治理
            - env: 环境问题，需要优化环境配置
            """, example = "bug",
            implementation = FailureReason.class)
    private String failureReason;

    @Schema(description = """
            补充说明
            Description
            """)
    private String description;

    @Schema(description = """
            标记人 ID
            Marker user ID
            """)
    private Long markedBy;

    @Schema(description = """
            标记时间
            Marked at
            """)
    private LocalDateTime markedAt;

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
     * 工厂方法：创建手动失败标记
     * <p>
     * Factory Method: Create manual failure mark
     *
     * @param executionId   执行记录 ID
     *                      Execution record ID
     * @param failureReason 失败原因
     *                      Failure reason (bug/flaky/env)
     * @param description   补充说明
     *                      Description
     * @param markedBy      标记人 ID
     *                      Marker user ID
     * @return 新手动失败标记
     * New manual failure mark
     * @throws IllegalArgumentException 当失败原因不合法时
     *                                  When failure reason is invalid
     */
    public static ManualFailureMark create(Long executionId, String failureReason, String description, Long markedBy) {
        /* 验证失败原因是否合法
         * Validate failure reason */
        if (!FailureReason.exists(failureReason)) {
            throw new IllegalArgumentException("Invalid failure reason: " + failureReason);
        }

        return ManualFailureMark.builder()
                .executionId(executionId)
                .failureReason(failureReason)
                .description(description)
                .markedBy(markedBy)
                .markedAt(LocalDateTime.now())
                .build();
    }

    /**
     * 更新失败原因
     * <p>
     * Update failure reason
     *
     * @param newReason   新失败原因
     *                    New failure reason
     * @param description 补充说明
     *                    Description
     * @param markedBy    标记人 ID
     *                    Marker user ID
     * @throws IllegalArgumentException 当失败原因不合法时
     *                                  When failure reason is invalid
     */
    public void updateReason(String newReason, String description, Long markedBy) {
        if (!FailureReason.exists(newReason)) {
            throw new IllegalArgumentException("Invalid failure reason: " + newReason);
        }
        this.failureReason = newReason;
        this.description = description;
        this.markedBy = markedBy;
        this.markedAt = LocalDateTime.now();
    }

}

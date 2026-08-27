package com.dwl.model.domain.execution.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.ddd.Entity;
import com.dwl.common.enums.DeletedStatus;
import com.dwl.common.enums.execution.ActionType;
import com.dwl.common.enums.execution.AssertResult;
import com.dwl.common.enums.execution.AssertType;
import com.dwl.common.enums.execution.LocatorStrategy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 执行步骤结果实体
 * <p>
 * Execution Step Result Entity
 * <p>
 * TaskExecution 聚合根内的实体, 记录每次执行中每个步骤的详细结果
 * <p>
 * Entity within the TaskExecution aggregate, recording detailed results
 * of each step during execution.
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-25 20:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("execution_step_result")
@Schema(description = """
        执行步骤结果实体
        Execution Step Result Entity
        """)
public class ExecutionStepResult extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = """
            步骤结果 ID
            Step result ID
            """)
    private Long id;

    @Schema(description = """
            执行记录 ID
            Execution record ID
            """, example = "1")
    private Long executionId;

    @Schema(description = """
            步骤序号
            Step order
            """, example = "1")
    private Integer stepOrder;

    @Schema(description = """
            动作类型
            Action type
            """, example = "click", implementation = ActionType.class)
    private String actionType;

    @Schema(description = """
            断言类型
            Assert type
            """, example = "visible", implementation = AssertType.class)
    private String assertType;

    @Schema(description = """
            断言结果
            Assert passed
            """, example = "1", implementation = AssertResult.class)
    private Integer assertPassed;

    @Schema(description = """
            实际值
            Actual value
            """)
    private Integer actualValue;

    @Schema(description = """
            步骤耗时
            Duration (milliseconds)
            """)
    private String durationMs;

    @Schema(description = """
            Error message
            """)
    private String errorMessage;

    @Schema(description = """
            使用的定位策略
            Locator strategy used
            """, example = "primary", implementation = LocatorStrategy.class)
    private String locatorUsed;

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
     * 工厂方法: 创建执行步骤结果
     * <p>
     * Factory Method: Create execution step result
     *
     * @param executionId  执行记录 ID
     *                     Execution record ID
     * @param stepOrder    步骤序号
     *                     Step order
     * @param actionType   动作类型
     *                     Action type
     * @param assertType   断言类型
     *                     Assert type
     * @param assertPassed 断言结果
     *                     Assert passed
     * @param actualValue  实际值
     *                     Actual value
     * @param durationMs   步骤耗时
     *                     Duration
     * @param errorMessage 错误信息
     *                     Error message
     * @param locatorUsed  定位策略
     *                     Locator strategy
     * @return 新执行步骤结果
     * New execution step result
     */
    public static ExecutionStepResult create(Long executionId, Integer stepOrder, String actionType,
                                             String assertType, Integer assertPassed, Integer actualValue,
                                             String durationMs, String errorMessage, String locatorUsed) {
        return ExecutionStepResult.builder()
                .executionId(executionId)
                .stepOrder(stepOrder)
                .actionType(actionType)
                .assertType(assertType)
                .assertPassed(assertPassed)
                .actualValue(actualValue)
                .durationMs(durationMs)
                .errorMessage(errorMessage)
                .locatorUsed(locatorUsed)
                .build();
    }

}

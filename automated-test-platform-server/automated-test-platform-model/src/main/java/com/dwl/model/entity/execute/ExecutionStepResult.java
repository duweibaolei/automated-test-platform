package com.dwl.model.entity.execute;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwl.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 执行步骤结果实体
 * Execution Step Result Entity
 * 对应表 execution_step_result,记录每次执行中每个步骤的详细结果
 * Maps to table execution_step_result, recording detailed results for each step in an execution
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-08-23 14:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("execution_step_result")
@Schema(description = """
        执行步骤结果
        Execution Step Result
        """)
public class ExecutionStepResult extends BaseEntity {

    @Schema(description = """
            执行记录 ID
            Execution record ID
            """, example = "1")
    private Long executionId;

    @Schema(description = """
            步骤序号
            Action type
            """, example = "1")
    private Integer stepOrder;

    @Schema(description = """
            动作类型
            Action type
            """, example = "click")
    private String actionType;

    @Schema(description = """
            断言类型
            Assert type
            """, example = "visible")
    private String assertType;

    @Schema(description = """
            断言结果: 1-通过 0-失败 NULL-无断言
            Assert result: 1-passed 0-failed null-no assertion
            """, example = "1")
    private Integer assertPassed;

    @Schema(description = """
            实际值
            Actual value
            """)
    private Integer actualValue;

    @Schema(description = """
            步骤耗时(毫秒)
            Step duration(milliseconds)
            """)
    private String durationMs;

    @Schema(description = """
            错误信息
            Error message
            """)
    private String errorMessage;

    @Schema(description = """
            使用的定位策略: primary backup
            Locator strategy used: primary backup
            """, example = "primary")
    private String locatorUsed;

}
